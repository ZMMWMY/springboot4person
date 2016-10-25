package com.data;

import com.alibaba.fastjson.JSON;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Z先生 on 2016/10/24.
 * 启动项目时加载数据
 */


@Component
//@Order 注解的执行优先级是按value值从小到大顺序。
@Order(value = 1)
public class MyDataRunner implements CommandLineRunner{
    @Autowired
    UserService userService;

    public void run(String... strings) throws Exception {
        System.out.println(1);
        System.out.println(JSON.toJSON(userService.getAllUser()));
    }
}
