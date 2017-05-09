package com.security.config.core;

import com.security.util.JwtTokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author : MrZ
 *
 * @Description Created in 10:58 on 2017/5/9.
 * Modified By :
 */
public class ValidTokenFilter extends OncePerRequestFilter {

    private static final String X_HEADER_NAME = "X-AUTH-TOKEN";

    private static final String X_HEADER_Head = "Bearer";


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
        if (token != null && !token.startsWith(X_HEADER_Head)) {


            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request, response);
    }
}
