package com.mrz.secKill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Author : MrZ
 *
 * @Description Created in 17:48 on 2017/5/18.
 * Modified By :
 */
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
@MapperScan(value = "com.mrz.secKill.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ThreadPoolTaskExecutor executor(){
        return new ThreadPoolTaskExecutor();
    }
}
