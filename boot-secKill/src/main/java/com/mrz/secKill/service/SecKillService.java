package com.mrz.secKill.service;

import com.mrz.secKill.cache.SuccessKillCache;
import com.mrz.secKill.cache.jedis.JedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : MrZ
 *
 * @Description Created in 17:41 on 2017/5/19.
 * Modified By :
 */
@Service
public class SecKillService {
    @Autowired
    SuccessKillCache successKillCache;


    public void secKill(String phone, String url) {

    }


    public void takeOrder(String phone, String url) {
        //校验库存

        //校验token 过期时间    如过期的话 在缓存的库存加1
        if (successKillCache.validToken(phone, url)) {
            //token校验通过

        } else {

        }
        //生成下单表   减去数据库库存
    }
}
