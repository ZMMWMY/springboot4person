package com.mrz.secKill.mapper;

import com.mrz.secKill.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.Map;

/**
 * Author : MrZ
 *
 * @Description Created in 14:49 on 2017/5/31.
 * Modified By :
 */
public interface OrderMapper {

    @Insert("insert into `order` (good_url,mobile,create_time,enable) values(#{goodUrl},#{mobile},#{createTime},1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createOrder(Order order);

}
