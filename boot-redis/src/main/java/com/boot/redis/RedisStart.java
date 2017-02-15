package com.boot.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

/**
 * Created by Z先生 on 2016/10/24.
 */
@SpringBootApplication
public class RedisStart {
    public static void main(String[] args) {
        SpringApplication.run(RedisStart.class, args);
    }


    public boolean acquireLock(String lockkey, int expired) {
        Jedis jedis = null;
       /* try {
            jedis = pool.getResource();
            long value = System.currentTimeMillis() + expired + 1;
            // 1. 通过SETNX试图获取一个lock
            long acquired = jedis.setnx(lockkey, String.valueOf(value));
            // SETNX成功，则成功获取一个锁
            if (acquired == 1)
                return true;
            // SETNX失败，说明锁仍然被其他对象保持，检查其是否已经超时
            String oldValueStr = jedis.get(lockkey);
            if (oldValueStr == null) // 被其他线程干扰，返回false，需重新获取锁操作
                return false;
            long oldValue = Long.valueOf(oldValueStr);
            // 超时
            if (oldValue < System.currentTimeMillis()) {
                String getValue = jedis.getSet(lockkey, String.valueOf(value));
                // 获取锁成功
                if (getValue == null || Long.valueOf(getValue) == oldValue)
                    return true;
                    // 已被其他进程捷足先登了
                else
                    return false;
            } else// 未超时，则直接返回失败
                return false;
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }*/
        return true;
    }
}
