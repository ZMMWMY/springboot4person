package com.security.config.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Author : MrZ
 *
 * @Description Created in 10:58 on 2017/5/9.
 * Modified By :
 */
@Slf4j
public class ValidTokenFilter extends OncePerRequestFilter {

    private static final String X_HEADER_NAME = "X-AUTH-TOKEN";

    private static final String X_HEADER_Head = "Bearer";
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDetailService customUserDetailService;

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        String token = request.getHeader(X_HEADER_NAME);
        token = request.getParameter(X_HEADER_NAME);
        log.info("传来的token" + token);
        if (token != null /*&& !token.startsWith(X_HEADER_Head)*/) {

//            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (/*username != null &&*/ SecurityContextHolder.getContext().getAuthentication() == null) {
                Claims claims = jwtTokenUtil.getClaimsFromToken(token);
                if (claims == null) {
                    //验证不通过
                    HttpServletResponse httpResponse = response;
                    httpResponse.setCharacterEncoding("UTF-8");
                    httpResponse.setContentType("application/json; charset=utf-8");
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    //将验证不通过的错误返回
                    ObjectMapper mapper = new ObjectMapper();

                    httpResponse.getWriter().write(mapper.writeValueAsString(new CustomResponse<>(CustomResponse.TOKEN_FAIL, CustomResponse.TOKEN_FAIL_MSG, null)));
                    return;
                } else {
                    //token组装成authentication放到上下文中,在后面的接口的校验权限中用到
                    UserDetails userDetails = customUserDetailService.loadUserByUsername((String) claims.get("sub"));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
