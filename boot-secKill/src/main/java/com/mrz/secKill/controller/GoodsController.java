package com.mrz.secKill.controller;

import com.mrz.secKill.model.Good;
import com.mrz.secKill.service.GoodService;
import com.mrz.secKill.util.SecKillUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Author : MrZ
 *
 * @Description Created in 10:23 on 2017/5/19.
 * Modified By :
 */
@Controller
public class GoodsController {

    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/good/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable Integer id) {
        Assert.notNull(id);
        Good good = goodService.findById(id);

        if (null == good) {
            return null;
        }
        Date start = good.getStartTime();
        Date end = good.getEndTime();
        long now = System.currentTimeMillis();
        if (now < start.getTime() || now > end.getTime()) {
            return null;
        }
        good.setLink(SecKillUtil.goodSecKill(id));
        return good;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        return goodService.findAllGood();
    }

    /**
     * @param : [id]
     * @return : void
     * @Author : Mr Z
     * @Description : 暴露秒杀接口
     * @Date 2017/5/19
     */
    @RequestMapping(value = "/good/{id}/link", method = RequestMethod.GET)
    @ResponseBody
    public Object goodLink(@PathVariable Integer id) {
        Good good = goodService.findById(id);
        if (null == good) {
            return null;
        }
        Date start = good.getStartTime();
        Date end = good.getEndTime();
        long now = System.currentTimeMillis();
        if (now < start.getTime() || now > end.getTime()) {
            return null;
        }
        return SecKillUtil.goodSecKill(id);

    }
}
