package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by Z先生 on 2016/10/24.
 */
@MapperScan("com.dao")
@SpringBootApplication
public class MybatisStart {
    public static void main(String[] args) {
        SpringApplication.run(MybatisStart.class, args);
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet servlet){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(servlet);
        registrationBean.getUrlMappings().clear();
        registrationBean.addUrlMappings("*.shtml");

        return  registrationBean;
    }

}
