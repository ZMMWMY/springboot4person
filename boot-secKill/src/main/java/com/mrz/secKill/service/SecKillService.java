package com.mrz.secKill.service;

import com.mrz.secKill.cache.SuccessKillCache;
import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.mapper.GoodMapper;
import com.mrz.secKill.mapper.OrderMapper;
import com.mrz.secKill.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodMapper goodMapper;


    public void secKill(String phone, String url) {

    }


    public void takeOrder(String phone, String url, Integer id) {
        //校验库存
        //判断是否有资格进行下单

        //校验token 过期时间    如过期的话 在缓存的库存加1
        if (successKillCache.validToken(phone, url)) {
            //token校验通过

        } else {
            throw new IllegalArgumentException("");
        }
        goodMapper.reduceGoodStock(id);
        //生成下单表   减去数据库库存
        Order order = new Order();
        order.setMobile(phone);
        order.setUrl(url);
        order.setCreateTime(new Date());
        orderMapper.createOrder(order);

    }
}
