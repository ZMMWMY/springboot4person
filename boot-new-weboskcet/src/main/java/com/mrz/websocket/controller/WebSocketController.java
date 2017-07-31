package com.mrz.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by ZMM on 2017/7/31.
 */
@Controller
public class WebSocketController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @MessageMapping(value = "/hello")
    @SendTo("/topic/hello")
    public String hello(String name) {
        logger.info("receive message :" + name);
        return name;
    }
}
