package com.mrz.secKill.service;

import com.mrz.secKill.mapper.GoodMapper;
import com.mrz.secKill.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 13:49 on 2017/5/19.
 * Modified By :
 */
@Service
public class GoodService {

    @Autowired
    GoodMapper goodMapper;

    public List<Good> findAllGood() {
        return goodMapper.findAllGood();
    }

    public Good findById(Integer id) {
        return goodMapper.findAllGoodById(id);
    }


    public void secKill(){

    }
}
