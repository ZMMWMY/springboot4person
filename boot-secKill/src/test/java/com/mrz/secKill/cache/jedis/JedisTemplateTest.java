package com.mrz.secKill.cache.jedis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        System.out.println(jedisTemplate.get("333"));
    }

    @Test
    public void hget(){
        System.out.println(jedisTemplate.hget("name:{0}123","137"));
    }

    @Test
    public void hset(){
        System.out.println(jedisTemplate.hset("name:{0}123","137","137"));
    }

    @Test
    public void hlen(){
        System.out.println(jedisTemplate.hlen("12343"));
    }

}