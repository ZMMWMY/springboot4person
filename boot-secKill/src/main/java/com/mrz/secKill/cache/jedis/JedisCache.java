package com.mrz.secKill.cache.jedis;

import com.mrz.secKill.cache.CacheManager;
import com.mrz.secKill.common.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

/**
 * Author : MrZ
 *
 * @Description Created in 13:57 on 2017/5/22.
 * Modified By :
 */
@Component
public class JedisCache implements CacheManager {

    @Autowired
    JedisTemplate jedisTemplate;

    public long decr(String key) {
        return jedisTemplate.decr(key);
    }


    public long incr(String key) {
        return jedisTemplate.incr(key);
    }


    public <T> T get(String key, Class<T> clz) {
        String result = get(key);
        if (result != null) {
            return ConvertUtil.unserialize(result.getBytes(), clz);
        }
        return null;

    }

    public <T> T blpop(String key, Class<T> clz) {
        return jedisTemplate.blpop(key, clz);
    }

    @Override
    public void hset(String key, String field, String value) {
        jedisTemplate.hset(key, field, value);
    }

    @Override
    public String hget(String key, String field) {
        return jedisTemplate.hget(key, field);
    }

    public void rpush(String key, String value) {
        jedisTemplate.rpush(key, value);
    }

    @Override
    public String get(String key) {
        return jedisTemplate.get(key);
    }

    @Override
    public Set<Object> getAll(String pattern) {
        return null;
    }

    @Override
    public void set(String key, Serializable value, int seconds) {
        jedisTemplate.set(key, value, seconds);
    }

    @Override
    public void set(String key, Serializable value) {
        jedisTemplate.set(key, value);
    }

    @Override
    public Boolean exists(String key) {
        return jedisTemplate.exist(key);
    }

    @Override
    public void del(String key) {

    }

    @Override
    public void delAll(String pattern) {

    }

    @Override
    public String type(String key) {
        return null;
    }

    @Override
    public Boolean expire(String key, int seconds) {
        return null;
    }

    @Override
    public Boolean expireAt(String key, long unixTime) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Object getSet(String key, Serializable value) {
        return null;
    }

    @Override
    public boolean setnx(String key, Serializable value) {
        return false;
    }

    @Override
    public void unlock(String key) {

    }


    @Override
    public void hdel(String key, String field) {

    }

    public long hlen(String key) {
        return jedisTemplate.hlen(key);
    }
}
