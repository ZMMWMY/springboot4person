package com.mrz.mongo;

import com.mrz.mongo.dao.UserDao;
import com.mrz.mongo.mapper.UserMapper;
import com.mrz.mongo.model.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Author : MrZ
 *
 * @Description Created in 11:42 on 2017/3/30.
 * Modified By :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class Test {

    @Autowired
    UserDao userDao;

    @org.junit.Test
    public void test1(){
        userDao.deleteAll();

        userDao.insert(new User(1L,"zmm"));
        userDao.insert(new User(2L,"wmy"));
        userDao.insert(new User(3L,"zmmwmy"));

        System.out.println(userDao.findAll().size());

        User user = userDao.findByName("zmm");

        System.out.println(user);

        userDao.delete(1L);

        user = userDao.findByName("zmm");

        System.out.println(user);

    }

    @Autowired
    UserMapper userMapper;

    @org.junit.Test
    public void test2(){
//        System.out.println(userMapper.findAll().size());
       mongoTemplate.insert(new User(1L,"zmm"));
    }

    @Resource
    private MongoTemplate mongoTemplate;
}
