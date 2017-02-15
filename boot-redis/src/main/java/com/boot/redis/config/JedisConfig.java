package com.boot.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Z先生 on 2017/2/15.
 */
@Configuration
public class JedisConfig {

    //配置RedisCacheManager对象。RedisCacheManager用于告诉spring boot将使用redis作为系统的缓存。
    @Bean
    CacheManager redisCacheManager(RedisTemplate<String, Object> objRedisTemplate) {
        return new RedisCacheManager(objRedisTemplate);
    }
}
