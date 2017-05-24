package com.mrz.secKill.mq;

import com.mrz.secKill.cache.GoodStockCache;
import com.mrz.secKill.cache.MobileBlackCache;
import com.mrz.secKill.cache.SuccessKillCache;
import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.cache.jedis.JedisTemplate;
import com.mrz.secKill.common.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author : MrZ
 *
 * @Description Created in 16:52 on 2017/5/23.
 * Modified By :
 */
@Component
public class MessageHandler implements Runnable {

    private static Logger logger = Logger.getLogger(MessageHandler.class);

    @Autowired
    JedisCache jedisCache;

    @Autowired
    JedisTemplate jedisTemplate;

    @Autowired
    MobileBlackCache mobileBlackCache;

    @Autowired
    GoodStockCache goodStockCache;

    @Autowired
    SuccessKillCache successKillCache;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public void run(String... strings) throws Exception {
       /* jedisTemplate.set("98fe8472289a52dc28140e4b799af03c" + Constant.CacheList.GOOD_STOCK_LIST, 100);
        jedisTemplate.set("98fe8472289a52dc28140e4b799af03c" + Constant.CacheList.SEC_KILL_LIMIT_LIST, 100);

        while (true) {
            //这个问题很大啊 会阻塞进程
            Message message = jedisCache.blpop("SEC_KILL_TYPE", Message.class);
            if (message == null) {
                logger.error("获取消息失败");
                Thread.sleep(5000);
            } else {
                //处理消息
                GoodMessage goodMessage = (GoodMessage) message.getContent();
                //黑名单校验
                if (mobileBlackCache.inBlackList(goodMessage.getMobile())) {
                    logger.error("黑名单用户");
                    return;
                }
                //库存校验
                if (!goodStockCache.stockExist(goodMessage.getUrl())) {
                    logger.error("已经被抢完了");
                }
                //减少库存操作
                if (!goodStockCache.decrStock(goodMessage.getUrl())) {
                    //FIXME 重试
                    logger.error("已经被抢完了");
                }
                //生成token存入
                successKillCache.saveToken(goodMessage.getMobile(), goodMessage.getUrl());
            }
        }*/
    }

    @PostConstruct
    public void start() {
        (new Thread(this)).start();
    }


    @Override
    public void run() {
        // 测试用
        jedisTemplate.set("98fe8472289a52dc28140e4b799af03c" + Constant.CacheList.SEC_KILL_LIMIT_LIST, 100);

        while (true) {
            //这个问题很大啊
            Message message = jedisCache.blpop("SEC_KILL_TYPE", Message.class);
            if (message == null) {
                logger.error("获取消息失败");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                threadPoolTaskExecutor.execute(() -> {
                    //处理消息
                    GoodMessage goodMessage = (GoodMessage) message.getContent();
                    //黑名单校验
                    if (mobileBlackCache.inBlackList(goodMessage.getMobile())) {
                        logger.error("黑名单用户");
                        return;
                    }
                    //库存校验
                    if (!goodStockCache.stockExist(goodMessage.getUrl())) {
                        logger.error("已经被抢完了");
                    }
                    //减少库存操作
                    if (!goodStockCache.decrStock(goodMessage.getUrl())) {
                        //FIXME 重试
                        logger.error("已经被抢完了");
                    }
                    //生成token存入
                    successKillCache.saveToken(goodMessage.getMobile(), goodMessage.getUrl());
                });

            }
        }
    }
}
