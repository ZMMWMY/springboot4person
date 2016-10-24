package com.web;

import com.domain.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Z先生 on 2016/10/24.
 */
@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public User test(){
        return  userService.findByName();
    }

    @RequestMapping(value = "/tx",method = RequestMethod.GET)
    public String tx(){
        userService.testTx();
        return "Tx";
    }
}
