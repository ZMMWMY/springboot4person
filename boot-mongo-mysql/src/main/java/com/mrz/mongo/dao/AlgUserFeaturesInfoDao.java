package com.mrz.mongo.dao;

import com.mrz.mongo.model.AlgUserFeaturesInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Author : MrZ
 *
 * @Description Created in 15:29 on 2017/3/30.
 * Modified By :
 */
public interface AlgUserFeaturesInfoDao extends MongoRepository<AlgUserFeaturesInfo,Integer>{

    List<AlgUserFeaturesInfo> findByType(String name);
}
