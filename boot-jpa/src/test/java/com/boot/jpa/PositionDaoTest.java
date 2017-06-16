package com.boot.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Author : MrZ
 *
 * @Description Created in 14:55 on 2017/6/16.
 * Modified By :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PositionDaoTest {

    @Autowired
    PositionDao positionDao;

    @Test
    public void test(){
        System.out.println(positionDao.count());
    }
}