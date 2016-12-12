package com.jersey.config;

import com.jersey.web.RestResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Z先生 on 2016/12/12.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RestResource.class);
        packages("com.jersey.web");
    }
}
