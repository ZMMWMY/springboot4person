package com.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        System.out.println(1);
        return template.getForObject("http://localhost:8082/resource/api/get",String.class);
    }

    @RequestMapping(value = "/rollback",method = {RequestMethod.GET,RequestMethod.POST})
    public String rollback(HttpServletRequest request, HttpServletResponse response){
        return template.getForObject("http://localhost:8082/resource/api/get",String.class);
    }

    @RequestMapping("resource.html")
    public String resPage(Model model){
        String data = "NONE";
        data = template.getForObject("http://localhost:8083/res/", String.class);
        model.addAttribute("data", data);
        return data;
    }
}
