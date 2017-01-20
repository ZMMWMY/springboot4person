package com.mrz.mybatis.again.mapper;

import com.mrz.mybatis.again.Start;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Z先生 on 2017/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        System.out.println(userMapper.selectByPrimaryKey(Long.parseLong("1")));
    }
}

