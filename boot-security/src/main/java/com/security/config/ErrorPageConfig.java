package com.security.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by Z先生 on 2016/11/19.
 */
@Configuration
public class ErrorPageConfig {

    /*
    Spring自带的EmbeddedServletContainerCustomizer进行设置。当Spring发现有类型为EmbeddedServletContainerCustomizer注册进来，
    便会调用EmbeddedServletContainerCustomizer的customize方法，此时，我们可以对整个Container进行设置，
    这里，我们添加了对于返回值为HttpStatus.FORBIDDEN的请求，将其交给/403进行处理。*/

    @Bean
    EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new MyError();
    }


    private static class MyError implements EmbeddedServletContainerCustomizer {

        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
        }
    }
}
