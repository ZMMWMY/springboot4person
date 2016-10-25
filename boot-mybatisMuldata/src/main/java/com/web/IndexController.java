package com.web;

import com.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Z先生 on 2016/10/25.
 */
@RestController
public class IndexController {

    @Autowired
    DataService dataService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Integer num1() {
        return dataService.userNum();
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Integer num2() {
        return dataService.orderNum();
    }

    @RequestMapping(value = "/tx",method = RequestMethod.GET)
    public void tx(){
        dataService.testTx();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Integer test() {
        return dataService.maxNum();
    }
}
