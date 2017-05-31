package com.mrz.secKill.task;

import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.Constant;
import com.mrz.secKill.model.Good;
import com.mrz.secKill.service.GoodService;
import com.mrz.secKill.util.SecKillUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 11:24 on 2017/5/26.
 * Modified By :
 */
@Component
public class StockTask {

    private static Logger logger = Logger.getLogger(StockTask.class);

    @Autowired
    GoodService goodService;

    @Autowired
    JedisCache jedisCache;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void stock() {
        logger.info("扫描开始");
        //每分钟执行一次
        //定时把数据库中的秒杀库存放到redis中
        //限流
        List<Good> list = goodService.findAllGood();
        for (Good good : list) {
            String stock = SecKillUtil.goodSecKill(good.getId()) + Constant.CacheList.GOOD_STOCK_LIST;
            String limit = SecKillUtil.goodSecKill(good.getId()) + Constant.CacheList.SEC_KILL_LIMIT_LIST;
            if (!jedisCache.exists(stock)) {
                logger.info("扫描到新增的秒杀商品" + good.getName());
                jedisCache.set(stock, good.getStock());
                jedisCache.set(limit, good.getStock());

            }
        }
        logger.info("扫描结束");
    }
}
