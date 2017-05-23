package com.mrz.secKill.cache;

import com.alibaba.fastjson.JSON;
import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
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
@Component
public class SuccessKillCache {

    @Autowired
    JedisCache jedisCache;

    public void saveToken(String mobile, String url) {
        Map result = new HashMap();
        result.put("timestamp", System.currentTimeMillis());
        result.put("token", token());
        jedisCache.set(getKey(mobile, url), JSON.toJSONString(result));
    }

    private String token() {
        return UUID.randomUUID().toString();
    }

    private String getKey(String mobile, String url) {
        return Constant.CacheList.SEC_KILL_SUCCESS + url + mobile;
    }
}
