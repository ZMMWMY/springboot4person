package com.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Z先生 on 2016/10/25.
 */


@Component
//@Order 注解的执行优先级是按value值从小到大顺序。
@Order(value = 2)
public class MyDataRunner2 implements CommandLineRunner{
    public void run(String... strings) throws Exception {
        System.out.println("22222222222222222222222222222222222222");
    }
}

