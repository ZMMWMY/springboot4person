package com.boot.redis;

import com.boot.redis.config.CacheTest;
import com.boot.redis.config.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Z先生 on 2017/2/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisStart.class)
public class RedisCacheTest {

    @Autowired
    CacheTest cacheTest;

    @Test
    public void test1(){
        User user = new User();
        user.setId(1);
        cacheTest.test2(user);
        cacheTest.test2(user);
    }
}
