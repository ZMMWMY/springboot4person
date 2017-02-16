package com.boot.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Z先生 on 2017/2/15.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{



    /**
     *
     * 生成key的策略
     * 实现自定义生成key策略,不需要为@Cacheeable 指定一个不同的键
     * @return
     *//*
     * */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                System.out.println(sb.toString());
                return sb.toString();
            }
        };
    }
    /**
     * RedisTemplate配置 基础配置 ,
     * you'll want to annotate one of your configuration classes with the @EnableCaching annotation and expose a CacheManager bean.
     * You'll also need a connection factory and a RedisTemplate bean for Spring to communicate with your Redis database
     *
     *
     * 另外  设置缓存对象的序列化方式,不设置会报错
     * 另外对于json序列化,对象要提供默认空构造器
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
    * 基础配置
    * 配置RedisCacheManager对象。RedisCacheManager用于告诉spring boot将使用redis作为系统的缓存。
    * */
    @Bean
    CacheManager redisCacheManager(RedisTemplate<String, String> redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }


}
