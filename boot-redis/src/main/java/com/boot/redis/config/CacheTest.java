package com.boot.redis.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z先生 on 2017/2/16.
 */
@Component
public class CacheTest {
    private static List<User> list = new ArrayList<User>();

    static {
        User user =new User(1,"wmy");
        User user1 =new User(2,"wmyzmm");
        User user2 =new User(3,"zmm");
        list.add(user);
        list.add(user1);
        list.add(user2);
    }


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

    @Cacheable(value = "user")
    public User getUser(Integer id){
        System.out.println("这不是缓存哦");
        return list.get(id);
    }

    @CachePut(value = "user")
    public User updateUser(User user){
        User user1=list.get(user.getId());
        user1.setName("mwz");
        return user1;
    }

    @CachePut(value = "user")
    public User updateUser(Integer io){
        User user1=list.get(io);
        user1.setName("mwz");
        System.out.println(list.get(io).getName());
        return user1;
    }


    @Cacheable(value = "common",key = "'id_'+#id")
    public User testKey(Integer id){
        System.out.println("这不是缓存哦");
        return list.get(id);
    }

    @CachePut(value = "common",key = "'id_'+#io")
    public User updateKey(Integer io){
        User user1=list.get(io);
        user1.setName("mwz");
        System.out.println(list.get(io).getName());
        return user1;
    }
}
