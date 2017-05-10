package com.security.controller;

import com.security.config.core.CustomResponse;
import com.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Author : MrZ
 *
 * @Description Created in 13:59 on 2017/5/9.
 * Modified By :
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public CustomResponse auth(@RequestParam(value = "username",required = false) String username,
                               @RequestParam(value = "password",required = false) String password) {
        Optional.of(username);
        Optional.of(password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(token);
        return CustomResponse.builder()
                .code(CustomResponse.CODE_SUCCESS).
                        msg(CustomResponse.MSG_SUCCESS).body(jwtTokenUtil.generateToken(username)).build();
    }

    @PostMapping(value = "/register")
    public Object register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password) {

        return ResponseEntity.ok();
    }


    @PostMapping(value = "/refresh")
    public Object refresh() {
        return ResponseEntity.ok();
    }
}
