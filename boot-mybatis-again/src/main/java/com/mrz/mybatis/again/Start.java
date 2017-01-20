package com.mrz.mybatis.again;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Z先生 on 2017/1/20.
 */
@MapperScan("com.mrz.mybatis.again.mapper")
@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
