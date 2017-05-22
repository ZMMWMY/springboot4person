package com.mrz.secKill.cache;

import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author : MrZ
 *
 * @Description Created in 15:01 on 2017/5/22.
 * Modified By :
 */
@Component
public class MobileBlackCache {

    @Autowired
    JedisCache jedisCache;


    /**
     * @param : [mobile]
     * @return : void
     * @Author : Mr Z
     * @Description : 是否存在黑名单
     * @Date 2017/5/22
     */
    public boolean inBlackList(String mobile) {
        if (mobile == null || null == jedisCache.hget(Constant.CacheList.MOBILEBACKLIST, mobile)) {
            return false;
        }
        return true;
    }


    /**
     * @param : [mobile]
     * @return : void
     * @Author : Mr Z
     * @Description : 添加黑名单用户
     * @Date 2017/5/22
     */
    public void addUser(String mobile) {
        jedisCache.hset(Constant.CacheList.MOBILEBACKLIST, mobile, "1");
    }
}
