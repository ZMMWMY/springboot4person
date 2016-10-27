package com.activemq.Consumer;

import com.config.MyWebSocket;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Z先生 on 2016/10/27.
 */
@Component
public class Consumer {


    //监听这个queue  消费者
    @JmsListener(destination = "myqueue")
    public void receiveQueue(String text) throws IOException {
        System.out.println(text);
        MyWebSocket.sendInfo(text);
    }
}
