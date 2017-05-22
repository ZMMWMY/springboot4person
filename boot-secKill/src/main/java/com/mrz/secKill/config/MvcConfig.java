package com.mrz.secKill.config;

import com.mrz.secKill.intercept.MobileIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author : MrZ
 *
 * @Description Created in 16:08 on 2017/5/22.
 * Modified By :
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MobileIntercept()).addPathPatterns("/kill/**");
        super.addInterceptors(registry);
    }
}
