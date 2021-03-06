package com.mrz.secKill.mapper;

import com.mrz.secKill.model.Good;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 13:56 on 2017/5/19.
 * Modified By :
 */
public interface GoodMapper {

    @Select("select id ,name , stock  , start_time,end_time from good where enable = 1")
    List<Good> findAllGood();

    @Select("select id ,name , stock  , start_time,end_time from good where enable = 1 and id =#{id}")
    Good findAllGoodById(Integer id);

    @Update("update good set stock = stock - 1 where id = #{id}")
    void reduceGoodStock(Integer id);

    @Insert("insert into good (name,stock) values(#{name},#{stock})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void add(Good good);
}
