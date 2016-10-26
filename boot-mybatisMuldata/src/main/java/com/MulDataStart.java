package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Z先生 on 2016/10/25.
 */
@SpringBootApplication
@EnableTransactionManagement
public class MulDataStart extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(MulDataStart.class, args);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //favorPathExtension表示支持后缀匹配
       configurer.favorPathExtension(false);
    }
}
