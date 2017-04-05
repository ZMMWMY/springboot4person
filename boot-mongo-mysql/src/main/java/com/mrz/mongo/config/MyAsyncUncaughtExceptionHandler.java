package com.mrz.mongo.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Author : MrZ
 *
 * @Description Created in 17:35 on 2017/3/31.
 * Modified By :
 */
public class MyAsyncUncaughtExceptionHandler implements
        AsyncUncaughtExceptionHandler {
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("异常");
    }
}
