package com.mrz.secKill.controller;

import com.mrz.secKill.common.Constant;
import com.mrz.secKill.model.Good;
import com.mrz.secKill.response.ObjectDataResponse;
import com.mrz.secKill.response.ObjectListResponse;
import com.mrz.secKill.service.GoodService;
import com.mrz.secKill.util.SecKillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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
    public ObjectDataResponse detail(@PathVariable Integer id) {
        Assert.notNull(id);
        Good good = goodService.findById(id);

        if (null == good) {
            return ObjectDataResponse.builder().code(Constant.SystemCode.DATA_NOTFIND).msg(Constant.SystemCode.DATA_NOTFIND_MSG).build();
        }
        Date start = good.getStartTime();
        Date end = good.getEndTime();
        long now = System.currentTimeMillis();
        if (now < start.getTime() || now > end.getTime()) {
            return ObjectDataResponse.builder().code(Constant.SystemCode.DATA_EXPIRED).msg(Constant.SystemCode.DATA_EXPIRED_MSG).body(good).build();
        }

        return ObjectDataResponse.builder().body(good).build();
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @ResponseBody
    public ObjectListResponse<Good> list() {
        List list = goodService.findAllGood();
        return ObjectListResponse.builder().dataSet(list).build();
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
    public Object url(@PathVariable Integer id) {
        Good good = goodService.findById(id);
        if (null == good) {
            return ObjectDataResponse.builder().code(Constant.SystemCode.DATA_NOTFIND).msg("秒杀不存在").build();
        }
        Date start = good.getStartTime();
        Date end = good.getEndTime();
        long now = System.currentTimeMillis();
        if (now < start.getTime() || now > end.getTime()) {
            return ObjectDataResponse.builder().code(Constant.SystemCode.DATA_EXPIRED).msg("秒杀结束").build();
        }
        return ObjectDataResponse.builder().body(SecKillUtil.goodSecKill(id)).build();

    }
}
