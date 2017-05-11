package com.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : MrZ
 *
 * @Description Created in 14:25 on 2017/5/11.
 * Modified By :
 */
@RestController
@RequestMapping(value = "/api")
public class MessageController {

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = "/get")
    public String get(){
        return "get　Success";
    }


    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping(value = "/get")
    public String getPost(){
        return "post　Success";
    }
}
