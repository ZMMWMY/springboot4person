package com.mrz.secKill.service;

import com.mrz.secKill.mapper.GoodMapper;
import com.mrz.secKill.mapper.OrderMapper;
import com.mrz.secKill.model.Good;
import com.mrz.secKill.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author : MrZ
 *
 * @Description Created in 10:42 on 2017/6/5.
 * Modified By :
 */
@Transactional
@Service
public class TxService {
    @Autowired
    GoodMapper goodMapper;

    @Autowired
    OrderMapper orderMapper;

    public void addGood() throws IllegalAccessException {

        try {
            addOrder();

            if(1 == 1){
                throw new RuntimeException("123");
            }
            Good good = new Good();
            good.setName("123");
            good.setStock(12);
            goodMapper.add(good);


        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("123");
        }

    }


    public void addOrder() {
        Order order = new Order();
        order.setGoodUrl("test");
        orderMapper.createOrder(order);
    }
}
