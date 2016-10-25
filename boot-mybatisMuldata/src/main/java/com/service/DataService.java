package com.service;

import com.domain.domain1.User;
import com.domain.domain2.Order;
import com.mapper.data1.UserMapper;
import com.mapper.data2.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Z先生 on 2016/10/25.
 */
@Service
public class DataService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    public Integer userNum(){
        return userMapper.getAllNum();
    }

    public Integer orderNum(){
        return orderMapper.getAllNum();
    }

    public Integer maxNum(){
        return userMapper.maxId();
    }


    @Transactional
    public void testTx(){
        User user=new User();
        user.setName("zmm");
        Order order=new Order();
        order.setCode("wmy");

        userMapper.insertUser(user);

        if(1==1){
            throw new RuntimeException("");
        }

        orderMapper.insertOrder(order);
    }
}
