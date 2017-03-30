package com.mrz.mongo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author : MrZ
 *
 * @Description Created in 11:32 on 2017/3/30.
 * Modified By :
 */
@SpringBootApplication
@MapperScan("com.mrz.mongo.mapper")
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }
}
