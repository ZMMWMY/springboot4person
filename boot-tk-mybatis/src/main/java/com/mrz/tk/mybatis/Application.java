package com.mrz.tk.mybatis;

import com.mrz.tk.mybatis.mapper.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ZMM on 2017/6/28.
 */
@SpringBootApplication
@MapperScan(value = "com.mrz.tk.mybatis.mapper",markerInterface = MyMapper.class)
public class Application {


    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }
}
