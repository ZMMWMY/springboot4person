package com.security.repository;

import com.security.SecurityStart;
import com.security.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author : MrZ
 *
 * @Description Created in 15:30 on 2017/5/9.
 * Modified By :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SecurityStart.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void add(){
        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));
        user.setUsername("zmm");

        user.setRole(Arrays.asList(User.ROLE.admin.toString()));
        userRepository.save(user);
    }

    @Test
    public void findOne(){
        List list = userRepository.findAll();
        System.out.println(list.size());
    }

    @Test
    public void update(){
        List list = userRepository.findAll();
        User user = (User) list.get(0);
        user.setRole(Arrays.asList("ROLE_ADMIN"));
        userRepository.save(user);
    }

}