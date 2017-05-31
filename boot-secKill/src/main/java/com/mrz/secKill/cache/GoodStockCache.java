package com.mrz.secKill.cache;

import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author : MrZ
 *
 * @Description Created in 13:39 on 2017/5/23.
 * Modified By :
 */
@Component
public class GoodStockCache {

    @Autowired
    JedisCache jedisCache;

    /**
     * @param : [url]
     * @return : boolean
     * @Author : Mr Z
     * @Description : false 表示没有货
     * @Date 2017/5/23
     */
    public boolean stockExist(String url) {
        Integer stock = jedisCache.get(getKey(url), Integer.class);
        if (stock == null || stock <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @param : [url]
     * @return : boolean
     * @Author : Mr Z
     * @Description : true 表示成功
     * @Date 2017/5/23
     */
    public boolean decrStock(String url) {
        String key = getKey(url);
        if (jedisCache.decr(key) >= 0) {
            return true;
        }
        jedisCache.incr(key);
        return false;
    }

    public void incrStock(String url) {
        String key = getKey(url);
        jedisCache.incr(key);
    }


    public String getKey(String key) {
        return key + Constant.CacheList.GOOD_STOCK_LIST;
    }
}
