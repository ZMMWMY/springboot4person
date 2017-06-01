package com.mrz.secKill.cache;

import com.alibaba.fastjson.JSON;
import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author : MrZ
 *
 * @Description Created in 17:33 on 2017/5/23.
 * Modified By :
 */
@Slf4j
@Component
public class SuccessKillCache {

    @Autowired
    JedisCache jedisCache;

    @Autowired
    GoodStockCache goodStockCache;

    @Autowired
    SecKillListCache secKillListCache;

    public void saveToken(String mobile, String url) {
        Map result = new HashMap();
        result.put("timestamp", System.currentTimeMillis());
        result.put("token", token());
        log.info("获取秒杀资格" + getKey(mobile, url)+"value:  "+JSON.toJSONString(result));
        jedisCache.set(getKey(mobile, url), result);
    }


    public boolean validToken(String mobile, String url) {
        String key = getKey(mobile, url);

        String temp = jedisCache.get(key);

        if (temp == null) {

            return false;
        }
        Map result = JSON.parseObject(temp, Map.class);
        long timestamp = (long) result.get("timestamp");
        String token = (String) result.get("token");


        if (System.currentTimeMillis() - timestamp >= Constant.SystemCode.TOKEN_EXPIRE_SECONDS) {
            //过期 加缓存中的库存
            goodStockCache.incrStock(url);
            //删除秒杀队列中的数据 能让他重新秒杀
            secKillListCache.del(mobile, url);
            //删除token
            jedisCache.del(key);
            return false;
        }
        //删除token
        jedisCache.del(key);
        return true;
    }

    public String queryToken(String mobile, String url) {
        String temp = jedisCache.get(getKey(mobile, url));
        if (temp == null) {
            return temp;
        }
        Map result = JSON.parseObject(temp, Map.class);

        String token = (String) result.get("token");

        return token;
    }


    private String token() {
        return UUID.randomUUID().toString();
    }

    private String getKey(String mobile, String url) {
        return Constant.CacheList.SEC_KILL_SUCCESS + url + mobile;
    }
}
