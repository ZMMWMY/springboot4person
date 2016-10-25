package com.mapper.data1;

import com.domain.domain1.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Z先生 on 2016/10/25.
 */
public interface UserMapper {

    @Select("select count(id) from t_user")
    Integer getAllNum();


    @Insert("insert into t_user (name) values(#{name})")
    boolean insertUser(User user);

//    @Select("select max(id) from t_user")
    Integer maxId();
}
