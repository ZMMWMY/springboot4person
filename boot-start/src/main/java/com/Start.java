package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Z先生 on 2016/11/18.
 */
@SpringBootApplication
@RestController
public class Start {
    @RequestMapping(value = "/test")
    public String te() {
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
}
