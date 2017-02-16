package com.boot.redis.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by Z先生 on 2017/2/16.
 */
@Component
public class CacheTest {


    /**
     *
     * value是一个组，我们可以把一个接口里面的所有value都定义为同一，然后key不同，或者说key以参数加个后缀区分
     * 如果key不指定，那么每次传不同的参数，value就会与新参数组合为一个新的标识，指向新的缓存。
     * */
    @Cacheable(value = "user",keyGenerator = "keyGenerator")
    public User test(){
        System.out.println("1111");
        return new User();
    }


    @Cacheable(value = "user",keyGenerator = "keyGenerator")
    public User test1(){
        System.out.println("2222");
        return new User();
    }

    @Cacheable(value = "user")
    public User test2(User user){
        System.out.println("3333");
        return new User();
    }
}
