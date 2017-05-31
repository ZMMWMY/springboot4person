package com.mrz.secKill.intercept;

import com.alibaba.fastjson.JSON;
import com.mrz.secKill.cache.MobileBlackCache;
import com.mrz.secKill.common.BeanUtils;
import com.mrz.secKill.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : MrZ
 *
 * @Description Created in 11:44 on 2017/5/19.
 * Modified By : 执行秒杀需要登录  计划用AOP实现
 */
@Configuration
public class MobileIntercept implements HandlerInterceptor {

    MobileBlackCache mobileBlackCache = (MobileBlackCache) BeanUtils.getBean("mobileBlackCache");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {response.setCharacterEncoding("utf-8");
        String mobile = request.getParameter("mobile");
        if(null == mobile){
            Map result = new HashMap();
            result.put("msg",Constant.SystemCode.PARAM_NOT_FIND_MSG);
            result.put("code", Constant.SystemCode.PARAM_NOT_FIND);
            printJson(result,response);
            return false;
        }
        if(mobileBlackCache.inBlackList(mobile)){
            Map result = new HashMap();
            result.put("msg",Constant.SystemCode.DATA_EXPIRED_MSG);
            result.put("code", Constant.SystemCode.DATA_EXPIRED);
            printJson(result,response);
            return false;
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    public void printJson(Map result ,HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();
    }
}
