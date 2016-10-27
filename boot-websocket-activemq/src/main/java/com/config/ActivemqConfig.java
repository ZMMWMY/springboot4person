package com.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * Created by Z先生 on 2016/10/27.
 */
//使用的是内置的activemq
@Configuration
public class ActivemqConfig {

    @Bean
    public Queue myQueue(){
        return new ActiveMQQueue("myqueue");
    }
}
