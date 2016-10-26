package com.service;

import com.dao.UserMapper;
import com.domain.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Z先生 on 2016/10/24.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findByName() {
        System.out.println(userMapper.maxId());
        return userMapper.findByName("test");
    }


    @Cacheable(value = "userCache", keyGenerator = "keyGenerator")
    public List<User> getAllUser(Integer currSize, Integer pageSize) {
        currSize = currSize == null ? 1 : currSize;
        pageSize = pageSize == null ? 1 : pageSize;
        System.out.println("没有缓存调用该方法");
        PageHelper.startPage(currSize, pageSize);
        return userMapper.getAll();
    }

    @Transactional
    public void testTx() {
        User user = new User();
        user.setName("zmm");
        user.setAge(23);
        userMapper.addUser(user);
        throw new RuntimeException("");
    }
}
