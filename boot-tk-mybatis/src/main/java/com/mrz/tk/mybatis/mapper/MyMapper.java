package com.mrz.tk.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * Created by ZMM on 2017/6/28.
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T>,SelectByIdsMapper<T>,ConditionMapper<T> {
}
