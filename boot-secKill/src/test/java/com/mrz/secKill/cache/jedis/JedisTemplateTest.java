package com.mrz.secKill.cache.jedis;


import com.mrz.secKill.common.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

/**
 * Author : MrZ
 *
 * @Description Created in 14:30 on 2017/5/22.
 * Modified By :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JedisTemplateTest {
    @Autowired
    JedisTemplate jedisTemplate;

    @Test
    public void get() throws Exception {
        System.out.println(jedisTemplate.get("98fe8472289a52dc28140e4b799af03c"));
    }

    @Test
    public void set() {
        jedisTemplate.set("98fe8472289a52dc28140e4b799af03c"+Constant.CacheList.GOOD_STOCK_LIST , 100);
        jedisTemplate.set("98fe8472289a52dc28140e4b799af03c"+Constant.CacheList.SEC_KILL_LIMIT_LIST , 100);
//        System.out.println(jedisTemplate.get("98fe8472289a52dc28140e4b799af03c"));
    }

    @Test
    public void hget() {
        System.out.println(jedisTemplate.hget("name:{0}123", "137"));
    }

    @Test
    public void hset() {
        System.out.println(jedisTemplate.hset("name:{0}123", "137", "137"));
    }

    @Test
    public void hlen() {
        System.out.println(jedisTemplate.hlen("12343"));
    }


    @Test
    public void incr() {
//        jedisTemplate.getJedis().set("123","0");
//
//        jedisTemplate.getJedis().decr("123");
//
//        System.out.println(jedisTemplate.getJedis().get("123"));

    }

}