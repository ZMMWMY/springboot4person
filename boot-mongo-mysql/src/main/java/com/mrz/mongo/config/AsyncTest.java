package com.mrz.mongo.config;

import org.springframework.scheduling.annotation.Async;

/**
 * Author : MrZ
 *
 * @Description Created in 17:35 on 2017/3/31.
 * Modified By :
 */
public class AsyncTest {

    @Async
    public void async(){
        if(1 == 1){
            throw new RuntimeException("");
        }
        System.out.println(Thread.currentThread().getName());
    }

    public final static long maxWorkerId = -1L ^ -1L << 4L;

    public static void main(String[] args) {
        System.out.println(maxWorkerId);
    }
}
