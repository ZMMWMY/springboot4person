package com.mrz.secKill.controller;

import com.mrz.secKill.cache.SuccessKillCache;
import com.mrz.secKill.common.Constant;
import com.mrz.secKill.response.ObjectDataResponse;
import com.mrz.secKill.service.GoodService;
import com.mrz.secKill.service.SecKillService;
import com.mrz.secKill.util.SecKillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : MrZ
 *
 * @Description Created in 17:34 on 2017/5/19.
 * Modified By :
 */
@Controller
@RequestMapping(value = "/kill")
public class SecKillController {
    @Autowired
    GoodService goodService;

    @Autowired
    SecKillService secKillService;

    @Autowired
    SuccessKillCache successKillCache;

    @PostMapping(value = "/{id}/{url}/action")
    @ResponseBody
    public Object secKill(@PathVariable Integer id,
                          @PathVariable String url,
                          @RequestParam String mobile) {
        Assert.notNull(id);
        Assert.notNull(url);
        Assert.notNull(mobile);

        if (!SecKillUtil.goodSecKill(id).equals(url)) {
            ObjectDataResponse.builder().code(202).msg(Constant.SystemCode.DATA_EXPIRED_MSG).build();
        }
        goodService.secKill(mobile, url);

        return ObjectDataResponse.builder().msg("正在为您抢购").build();
    }


    /**
     * @param : [phone, md5]
     * @return : java.lang.Object
     * @Author : Mr Z
     * @Description : 轮询查询是否秒杀成功
     * @Date 2017/5/19
     */
    @PostMapping(value = "/query")
    @ResponseBody
    public Object queryResult(@RequestParam String mobile,
                              @RequestParam String url) {

        return ObjectDataResponse.builder().body(successKillCache.queryToken(mobile, url)).build();

    }

    /**
     * @param : [phone, md5, token]
     * @return : java.lang.Object
     * @Author : Mr Z
     * @Description : 根据token来下单 ,在这里对商品数据库的库存进行操作
     * @Date 2017/5/19
     */
    @PostMapping(value = "/takeOrder")
    public Object order(@RequestParam String mobile,
                        @RequestParam String url,
                        @RequestParam Integer id) {
        secKillService.takeOrder(mobile, url,id);
        return ObjectDataResponse.builder().body("SUCCESS").build();
    }


    @GetMapping(value = "/now")
    @ResponseBody
    public ResponseEntity now() {
        return new ResponseEntity<>(System.currentTimeMillis(), HttpStatus.OK);

    }
}
