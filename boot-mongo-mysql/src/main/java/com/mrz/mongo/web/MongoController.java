package com.mrz.mongo.web;

import com.mrz.mongo.config.AsyncTest;
import com.mrz.mongo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : MrZ
 *
 * @Description Created in 11:34 on 2017/3/30.
 * Modified By :
 */
@RestController
public class MongoController {

    @Autowired
    UserDao userDao;

    @Autowired
    AsyncTest asyncTest;

    @GetMapping(value = "/insert")
    public Object insert(){
        return userDao.findByName("周齐明");
    }

    @GetMapping(value = "/test")
    public String te(){
        asyncTest.async();
        return "hello";
    }
}
