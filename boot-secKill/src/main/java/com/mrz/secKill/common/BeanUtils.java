package com.mrz.secKill.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Author : MrZ
 *
 * @Description Created in 11:10 on 2017/3/29.
 * Modified By :
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);

    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }
}
