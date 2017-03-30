package com.mrz.mongo.mapper;

import com.mrz.mongo.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 14:50 on 2017/3/30.
 * Modified By :
 */
public interface UserMapper {

    @Select("select * from city")
    List<User> findAll();
}
