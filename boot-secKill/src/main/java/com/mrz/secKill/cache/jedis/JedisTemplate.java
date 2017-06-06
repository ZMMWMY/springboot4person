package com.mrz.secKill.cache.jedis;

import com.alibaba.fastjson.JSON;
import com.mrz.secKill.common.ConvertUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;

import java.io.Serializable;
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

    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    private int expireMsecs = 60 * 1000;

    /**
     * 锁等待时间，防止线程饥饿
     */
    private int timeoutMsecs = 10 * 1000;

    private volatile boolean locked = false;

    /**
     * Lock key path.
     */
    private String lockKey;


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

    public synchronized void release(Jedis jedis) {
        if (locked) {
            jedis.del(lockKey);
        }
        locked = false;
    }


    public synchronized boolean acquire(Jedis jedis) throws InterruptedException {
        int timeout = 10000;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            String expiresStr = String.valueOf(expires); //锁到期时间

            if (jedis.setnx(lockKey, expiresStr) == 1) {
                // lock acquired
                locked = true;
                return true;
            }

            String currentValueStr = jedis.get(lockKey); //redis里的时间
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                // lock is expired

                String oldValueStr = jedis.getSet(lockKey, expiresStr);
                //获取上一个锁到期时间，并设置现在的锁到期时间，
                //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    //如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                    // lock acquired
                    locked = true;
                    return true;
                }
            }
            timeout -= 100;
            Thread.sleep(100);
        }
        return false;
    }


    public boolean exist(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error("exist " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return false;
    }

    public void del(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("del " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return;
    }


    public void hdel(String key, String filed) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hdel(key, filed);
        } catch (Exception e) {
            logger.error("hdel " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return;
    }


    public long decr(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.decr(key);
        } catch (Exception e) {
            logger.error("decr " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return -1;
    }

    public long incr(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        } catch (Exception e) {
            logger.error("decr " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return -1;
    }


    public <T> T blpop(String key, Class<T> clz) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();

            List list =  jedis.blpop(0,key);
            if (!CollectionUtils.isEmpty(list)) {
                String s = (String) list.get(1);
                logger.info("***************************" + s);
                return JSON.parseObject(s, clz);
//                return ConvertUtil.unserialize(s.getBytes(), clz);
            }
        } catch (Exception e) {
            logger.error("blpop " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
        return null;
    }

    public void rpush(String key, String value) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key, value);
        } catch (Exception e) {
            logger.error("rpush " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
    }

    public String get(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("find " + key + " failed  !", e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public void set(String key, Object value) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            set(key, value, -1);
        } catch (Exception e) {
            logger.error("set " + key + " failed  !", e);
        } finally {
            close(jedis);
        }
    }


    public void set(String key, Object value, Integer EXPIRE) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, JSON.toJSONString(value));
            if (EXPIRE > 0) {
                jedis.expire(key, EXPIRE);
            }
        } catch (Exception e) {
            logger.error("set " + key + " failed  !", e);
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
            logger.error("keys jedis failed!", e);
            return new HashSet<>();
        } finally {
            close(jedis);
        }
    }


    public String hget(String key, String field) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("hget jedis failed!", e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public long hlen(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.hlen(key);
        } catch (Exception e) {
            logger.error("hlen jedis failed! key = " + key, e);
            return 0;
        } finally {
            close(jedis);
        }
    }


    public boolean hset(String key, String field, String value) {
        return hset(key, field, value, -1);
    }

    public boolean hset(String key, String field, String value, Integer EXPIRE) {
        ShardedJedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key, field, value);
            if (EXPIRE > 0) {
                jedis.expire(key, EXPIRE);
            }
        } catch (Exception e) {
            logger.error("hset jedis failed!", e);
            return false;
        } finally {
            close(jedis);
        }
        return true;
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

