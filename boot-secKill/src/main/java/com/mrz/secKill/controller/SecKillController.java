package com.mrz.secKill.controller;

import com.mrz.secKill.common.Constant;
import com.mrz.secKill.response.ObjectDataResponse;
import com.mrz.secKill.service.GoodService;
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

    @PostMapping(value = "/{id}/{md5}/action")
    @ResponseBody
    public Object secKill(@PathVariable Integer id,
                          @PathVariable String md5,
                          @RequestParam String mobile) {
        Assert.notNull(id);
        Assert.notNull(md5);
        Assert.notNull(mobile);

        if (!SecKillUtil.goodSecKill(id).equals(md5)) {
            ObjectDataResponse.builder().code(202).msg(Constant.SystemCode.DATA_EXPIRED_MSG).build();
        }
        goodService.secKill(mobile, md5);

        return ObjectDataResponse.builder().msg("正在为您抢购").build();
    }

    public static void main(String[] args) {
        System.out.println(SecKillUtil.goodSecKill(1));
    }

    /**
     * @param : [phone, md5]
     * @return : java.lang.Object
     * @Author : Mr Z
     * @Description : 轮询查询是否秒杀成功
     * @Date 2017/5/19
     */
    @PostMapping(value = "/query")
    public Object queryResult(@RequestParam String phone,
                              @RequestParam String md5) {

        return null;
    }

    /**
     * @param : [phone, md5, token]
     * @return : java.lang.Object
     * @Author : Mr Z
     * @Description : 根据token来下单
     * @Date 2017/5/19
     */
    @PostMapping(value = "/takeOrder")
    public Object order(@RequestParam String phone,
                        @RequestParam String md5,
                        @RequestParam String token) {

        return null;
    }


    @GetMapping(value = "/now")
    @ResponseBody
    public ResponseEntity now() {
        return new ResponseEntity<>(System.currentTimeMillis(), HttpStatus.OK);

    }
}
