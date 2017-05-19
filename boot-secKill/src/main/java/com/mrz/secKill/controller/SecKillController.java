package com.mrz.secKill.controller;

import com.mrz.secKill.util.SecKillUtil;
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
public class SecKillController {

    @PostMapping(value = "/{id}/{md5}/action")
    public Object secKill(@PathVariable Integer id,
                          @PathVariable String md5,
                          @RequestParam String phone) {
        Map<String, Object> result = new HashMap();

        Assert.notNull(id);
        Assert.notNull(md5);
        Assert.notNull(phone);

        if (SecKillUtil.goodSecKill(id).equals(md5)) {
            result.put("msg", "抢购已经结束");
        }

        return result;
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
     * @Author : Mr Z
     * @Description : 根据token来下单
     * @param : [phone, md5, token]
     * @return : java.lang.Object
     * @Date 2017/5/19
     */
    @PostMapping(value = "/takeOrder")
    public Object order(@RequestParam String phone,
                        @RequestParam String md5,
                        @RequestParam String token){

        return null;
    }


    @GetMapping(value = "/now")
    @ResponseBody
    public Object now(){
        return System.currentTimeMillis();
    }
}
