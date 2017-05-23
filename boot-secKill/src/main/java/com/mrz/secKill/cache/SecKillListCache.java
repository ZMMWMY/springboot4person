package com.mrz.secKill.cache;

import com.alibaba.fastjson.JSON;
import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
import com.mrz.secKill.mq.GoodMessage;
import com.mrz.secKill.mq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author : MrZ
 *
 * @Description Created in 14:40 on 2017/5/23.
 * Modified By :
 */
@Component
public class SecKillListCache {

    @Autowired
    JedisCache jedisCache;

    //放入秒杀队列中
    public void pushList(String mobile, String url) {
        if (exist(mobile, url)) {
            throw new IllegalArgumentException("正在为您抢购中,请稍后");
        }
        push(mobile, url);
        Message message = new Message("SEC_KILL_TYPE", new GoodMessage(mobile, url));
        jedisCache.rpush(message.getKey(), JSON.toJSONString(message.getContent()));
    }

    private void push(String mobile, String url) {
        jedisCache.hset(getKey(url), mobile, mobile);
    }

    private boolean exist(String mobile, String url) {
        return jedisCache.hget(getKey(url), mobile) != null;
    }

    private String getKey(String key) {
        return key + Constant.CacheList.DO_SEC_KILL_LIST;
    }
}
