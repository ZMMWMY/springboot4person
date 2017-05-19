package com.mrz.secKill.mapper;

import com.mrz.secKill.model.Good;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 13:56 on 2017/5/19.
 * Modified By :
 */
public interface GoodMapper {

    @Select("select id ,name , stock , link , start_time,end_time where enable = 1")
    List<Good> findAllGood();

    @Select("select id ,name , stock , link , start_time,end_time where enable = 1 and id =#{id}")
    Good findAllGoodById(Integer id);


}
