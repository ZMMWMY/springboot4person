package com.boot.redis.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
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
            /** 重写生成key方法 */
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder("zmm");
                CacheConfig cacheConfig = o.getClass().getAnnotation(CacheConfig.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (cacheConfig != null && sb.toString().equals("zmm")) {
                    String[] cacheNames = cacheConfig.cacheNames();
                    if (cacheNames != null && cacheNames.length > 0) {
                        sb.append(cacheNames[0]);
                    }
                }
                if (sb.toString().equals("zmm")) {
                    sb.append(o.getClass().getName());
                }
                sb.append(":");
                if (objects != null) {
                    if (objects.length == 1) {
                        if (objects[0] instanceof Number || objects[0] instanceof String
                                || objects[0] instanceof Boolean) {
                            sb.append(objects[0]);
                        } else {
                            try {
                                sb.append(objects[0].getClass().getMethod("getId").invoke(objects[0]));
                            } catch (Exception e) {
                                if (objects[0] instanceof Map && ((Map<?, ?>) objects[0]).get("id") != null) {
                                    sb.append(((Map<?, ?>) objects[0]).get("id"));
                                } else {
                                    sb.append(JSON.toJSON(objects[0]));
                                }
                            }
                        }
                    } else {
                        sb.append(StringUtils.join(objects, ","));
                    }
                } else {
                    sb.append(method.getName());
                }
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
