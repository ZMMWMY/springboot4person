package com.mrz.secKill.cache.jedis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.*;

/**
 * Author : MrZ
 *
 * @Description Created in 14:00 on 2017/5/22.
 * Modified By :
 */
@Component
public class JedisTemplate {

    private static Logger logger = Logger.getLogger(JedisTemplate.class);

    private static Integer EXPIRE = 600;


    private static ShardedJedisPool shardedJedisPool = null;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    // 获取线程
    private ShardedJedis getJedis() {
        if (shardedJedisPool == null) {
            synchronized (EXPIRE) {
                if (shardedJedisPool == null) {
                    JedisPoolConfig poolConfig = jedisConnectionFactory.getPoolConfig();
                    JedisShardInfo shardInfo = jedisConnectionFactory.getShardInfo();
                    List<JedisShardInfo> list = new ArrayList<>();
                    list.add(shardInfo);
                    shardedJedisPool = new ShardedJedisPool(poolConfig, list);
                }
            }
        }
        return shardedJedisPool.getResource();
    }

    public String get(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            return null;
        } finally {
            close(jedis);
        }
    }


    public Set<String> keys(java.lang.String keyPattern) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            Jedis jedis1 = jedis.getShard(keyPattern);
            return jedis1.keys(keyPattern);
        } catch (Exception e) {
            return new HashSet<>();
        } finally {
            close(jedis);
        }
    }


    private void close(ShardedJedis jedis) {
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("close jedis failed!", e);
        }
    }
}

