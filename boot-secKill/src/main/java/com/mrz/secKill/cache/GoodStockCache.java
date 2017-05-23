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

    public boolean stockExist(String url) {
        Integer stock = jedisCache.get(getKey(url), Integer.class);
        if (stock == null || stock <= 0) {
            return false;
        }
        return true;
    }


    public String getKey(String key) {
        return key + Constant.CacheList.GOOD_STOCK_LIST;
    }
}
