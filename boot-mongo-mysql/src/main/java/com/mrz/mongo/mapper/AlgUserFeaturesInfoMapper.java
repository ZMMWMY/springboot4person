package com.mrz.mongo.mapper;

import com.mrz.mongo.model.AlgUserFeaturesInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 15:31 on 2017/3/30.
 * Modified By :
 */
public interface AlgUserFeaturesInfoMapper {


    @Select("select * from alg_user_features_info")
    List<AlgUserFeaturesInfo> findAll();

    @Select("select * from alg_user_features_info where fid = #{id}")
    AlgUserFeaturesInfo findById(Integer id);
}
