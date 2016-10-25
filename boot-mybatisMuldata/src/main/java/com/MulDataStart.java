package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Z先生 on 2016/10/25.
 */
@SpringBootApplication
@EnableTransactionManagement
public class MulDataStart {
    public static void main(String[] args) {
        SpringApplication.run(MulDataStart.class, args);
    }
}
