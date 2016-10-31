package com.consumer;

import api.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Z先生 on 2016/10/31.
 */
@RestController
public class TestController {

    @Reference
    UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return userService.getUserById(1).toString();
    }
}
