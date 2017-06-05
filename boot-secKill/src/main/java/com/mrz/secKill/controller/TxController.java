package com.mrz.secKill.controller;

import com.mrz.secKill.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : MrZ
 *
 * @Description Created in 10:55 on 2017/6/5.
 * Modified By :
 */
@RestController
public class TxController {
    @Autowired
    TxService txService;


    @GetMapping(value = "/tx")
    public void tx() throws IllegalAccessException {
        txService.addGood();
    }
}
