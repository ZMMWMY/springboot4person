package com.mrz.secKill.common;

/**
 * Author : MrZ
 *
 * @Description Created in 16:36 on 2017/5/19.
 * Modified By :
 */
public class Constant {

    public interface SystemCode {
        int DATA_NOTFIND = 201;
        String DATA_NOTFIND_MSG = "此商品不存在";

        int DATA_EXPIRED = 202;
        String DATA_EXPIRED_MSG = "抢购已结束";

        int PARAM_NOT_FIND = 203;
        String PARAM_NOT_FIND_MSG = "参数异常";

        /**
         * 撒点盐
         */
        String SALT = "gaggfvqwtebq23fvf1fcwrv**2gvfb3c3%2fgfv";
    }


    public interface CacheList {

        String MOBILEBACKLIST = "MOBILE_BLACK_LIST";

        //流量队列
        String SEC_KILL_NAME_LIST = ":SEC_KILL_NAME_LIST";

        String GOOD_STOCK_LIST = "GOOD_STOCK_LIST";

    }


    public interface GoodLimiter{


    }


}
