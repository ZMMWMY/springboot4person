package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Z先生 on 2016/10/24.
 */
public interface UserMapper {

    @Select("select * from user where name =#{name}")
    User findByName(String name);

    Integer maxId();


    @Select("select * from user")
    List<User> getAll();


    @Insert("insert into `user` (NAME,age) VALUES(#{name},#{age})")
    boolean addUser(User user);
}
