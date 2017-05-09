package com.security.controller;

import com.security.util.JwtTokenUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object auth(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenUtil.generateToken(username);
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
