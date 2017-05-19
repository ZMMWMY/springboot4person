package com.mrz.secKill.controller;

import com.mrz.secKill.util.SecKillUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
