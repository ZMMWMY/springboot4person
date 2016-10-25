package com.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Z先生 on 2016/10/25.
 */

@Configuration
@MapperScan(value = "com.mapper.data2",sqlSessionFactoryRef = "secondSqlSessionFactory")
public class DataSource2Config {

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.domain.domain2");
        return sessionFactory.getObject();
    }

}
