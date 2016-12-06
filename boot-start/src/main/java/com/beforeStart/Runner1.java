package com.beforeStart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Z先生 on 2016/11/18.
 */
@Component
@Order(1)
public class Runner1 implements CommandLineRunner{

    public void run(String... strings) throws Exception {
        System.out.println(1);
    }
}
