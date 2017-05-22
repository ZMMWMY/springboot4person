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
        System.out.println(jedisTemplate.get("123"));
    }

    @Test
    public void hget(){
        System.out.println(jedisTemplate.hget("123","123"));
    }

}