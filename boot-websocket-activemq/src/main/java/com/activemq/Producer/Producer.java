package com.activemq.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Z先生 on 2016/10/27.
 */
@Component
@EnableScheduling
//在启动的时候跑一次   可删除这个实现
public class Producer implements CommandLineRunner{
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
//    @Qualifier("myqueue")
    private Queue queue;


    final ExecutorService pushExecutor = Executors.newFixedThreadPool(10);

    //用线程池来发送消息
    void push(Object info){
        pushExecutor.execute(new Runnable() {
            public void run() {
                jmsTemplate.convertAndSend(queue,"msg");
            }
        });
    }

    public void run(String... strings) throws Exception {
        push(null);
    }


    //定时器   可删除
    @Scheduled(fixedDelay=3000)//每3s执行1次
    public void send() {
        this.jmsTemplate.convertAndSend(this.queue, "hi,activeMQ");
    }
}
