package com.mrz.secKill.service;

import com.mrz.secKill.cache.limit.RateLimiter;
import com.mrz.secKill.mapper.GoodMapper;
import com.mrz.secKill.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 13:49 on 2017/5/19.
 * Modified By :
 */
@Service
public class GoodService {


    @Autowired
    GoodMapper goodMapper;

    public List<Good> findAllGood() {
        return goodMapper.findAllGood();
    }

    public Good findById(Integer id) {
        return goodMapper.findAllGoodById(id);
    }


    /**
     * @param : [mobile, url]
     * @return : void
     * @Author : Mr Z
     * @Description :
     * @Date 2017/5/23
     */
    public void secKill(String mobile, String url) {
        //抢购是否结束
        //限流
        RateLimiter.getIstance().limitFlow(url);
        //是否在抢购队列

        //进入抢购队列
        //发送消息
    }
}