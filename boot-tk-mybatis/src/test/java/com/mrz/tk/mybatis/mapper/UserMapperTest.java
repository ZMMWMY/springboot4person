package com.mrz.tk.mybatis.mapper;

import com.mrz.tk.mybatis.Application;
import com.mrz.tk.mybatis.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ZMM on 2017/6/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void find() {
        List<User> list = userMapper.selectAll();
        System.out.println(list.size());
    }

    @Test
    public void findExample() {
        User user = new User();
        user.setUsername("123");
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        List list = userMapper.selectByExample(example);
        System.out.println(list.size());

        List list1 = userMapper.select(user);
        System.out.println(list1.size());
    }

}