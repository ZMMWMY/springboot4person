package com.security.repository;

import com.security.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Author : MrZ
 *
 * @Description Created in 15:16 on 2017/5/9.
 * Modified By :
 */
public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);

}
