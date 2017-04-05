package com.mrz.mongo;

import com.mrz.mongo.config.AsyncTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Author : MrZ
 *
 * @Description Created in 11:32 on 2017/3/30.
 * Modified By :
 */
@SpringBootApplication
@MapperScan("com.mrz.mongo.mapper")
@EnableAsync
public class MongoApplication implements CommandLineRunner {
    @Autowired
    AsyncTest asyncTest;

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);

    }

    @Order(1)
    public void run(String... strings) throws Exception {
       // asyncTest.async();
    }
}
