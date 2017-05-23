package com.mrz.secKill.cache.limit;

import com.mrz.secKill.cache.jedis.JedisCache;
import com.mrz.secKill.common.BeanUtils;
import com.mrz.secKill.common.Constant;

/**
 * Author : MrZ
 *
 * @Description Created in 17:55 on 2017/5/22.
 * Modified By :
 */
public class RateLimiter {

    // 定义一个私有构造方法
    private RateLimiter() {

    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile RateLimiter instance;

    //定义一个共有的静态方法，返回该类型实例
    public static RateLimiter getIstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (RateLimiter.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new RateLimiter();
                }
            }
        }
        return instance;
    }


    JedisCache jedisCache = (JedisCache) BeanUtils.getBean("jedisCache");

    //获取当前流量
    public long getCurrentFlow(String key) {
        return jedisCache.hlen(getKey(key));
    }


    public String getKey(String key) {
        return key + Constant.CacheList.SEC_KILL_NAME_LIST;
    }

    //获取商品总流量
    //定时器加载商品库存设置为流量阀值
    public long getLimitFlow(String key) {
        String num = String.valueOf(jedisCache.get(getKey(key)));
        return Long.parseLong(num);
    }

    //FIXME 规避null
    public void limitFlow(String key) {
        if (getCurrentFlow(key) >= getLimitFlow(key)) {
            throw new IllegalArgumentException("抱歉,没有挤进去");
        }
    }
}
