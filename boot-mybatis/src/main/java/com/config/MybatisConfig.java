package com.config;

import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Z先生 on 2016/10/24.
 */
@Configuration
public class MybatisConfig{

    @Autowired
    DataSource dataSource;

    private static Logger logger=Logger.getLogger(MybatisConfig.class);

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
