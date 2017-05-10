package com.security.util

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.security.entity.User
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * Author : MrZ 
 * @Description
 * Created in 15:21 on 2017/5/9.
 * Modified By : 
 */
class JwtTokenUtilTest {
    static void main(String[] args) {
        Mongo mongo = new MongoClient("119.23.127.232",32768)
        MongoTemplate mongoTemplate = new MongoTemplate(mongo,"security")
        mongoTemplate.collectionExists(User.class)
    }
}
