package com.mapper.data2;

import com.domain.domain2.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Z先生 on 2016/10/25.
 */
public interface OrderMapper {

    @Select("select count(id) from t_order")
    Integer getAllNum();

    @Insert("insert into t_order (code)   values(#{code})")
    boolean insertOrder(Order order);

   // @Select("select max(id) from t_order")
    Integer maxId();
}
