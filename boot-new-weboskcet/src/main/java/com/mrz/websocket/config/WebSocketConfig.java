package com.mrz.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by ZMM on 2017/7/31.
 */
@EnableWebSocketMessageBroker//enables WebSocket message handling, backed by a message broker.
@Configuration
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册webSocket节点,并开始socketjs,以备websocket不可用
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocket").setAllowedOrigins("*").withSockJS();
    }


    /**
     * 开启一个基于内存的消息节点,命名成topic
     * 并且设定url前缀为app,可通过@MessageMapping,使用方法相当于@RequireMapping
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
