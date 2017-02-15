package com.boot.redis.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by Z先生 on 2017/2/15.
 */
@Component
public class RedisCache {

    @Cacheable(value = "test")
    public String getStr(){
        System.out.println("这不是缓存啊test");
        return "这不是缓存啊test";
    }
}
