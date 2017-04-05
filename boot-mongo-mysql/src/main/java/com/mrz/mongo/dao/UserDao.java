package com.mrz.mongo.dao;

import com.mrz.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author : MrZ
 *
 * @Description Created in 11:35 on 2017/3/30.
 * Modified By :
 */

public interface UserDao extends MongoRepository<User,Long>{

    User findByName(String name);

    void deleteByName(String name);
}
