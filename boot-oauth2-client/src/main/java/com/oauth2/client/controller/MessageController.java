package com.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : MrZ
 *
 * @Description Created in 15:35 on 2017/5/11.
 * Modified By :
 */
@RestController
public class MessageController {

    @Autowired
    OAuth2RestTemplate template;

    @GetMapping(value = "/get")
    public String get(){
        return template.getForObject("localhost:8082/resource/api/get",String.class);
    }
}
