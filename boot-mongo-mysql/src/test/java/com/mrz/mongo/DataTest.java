package com.mrz.mongo;

import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mrz.mongo.dao.AlgUserFeaturesInfoDao;
import com.mrz.mongo.dao.UserDao;
import com.mrz.mongo.mapper.AlgUserFeaturesInfoMapper;
import com.mrz.mongo.model.AlgUserFeaturesInfo;
import com.mrz.mongo.model.User;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

/**
 * Author : MrZ
 *
 * @Description Created in 15:28 on 2017/3/30.
 * Modified By :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MongoApplication.class)
public class DataTest {

    @Autowired
    AlgUserFeaturesInfoDao algUserFeaturesInfoDao;

    @Autowired
    AlgUserFeaturesInfoMapper algUserFeaturesInfoMapper;

    @Resource
    private MongoTemplate mongoTemplate;


    @org.junit.Test
    public void test() {

        long btime = System.currentTimeMillis();
        algUserFeaturesInfoDao.deleteAll();

        List<AlgUserFeaturesInfo> list = algUserFeaturesInfoMapper.findAll();

        System.out.println(list.size());

        mongoTemplate.insertAll(list);

        long etime = System.currentTimeMillis();

        System.out.println(etime - btime);
    }

    @Test
    public void test5() {
        AlgUserFeaturesInfo info = algUserFeaturesInfoDao.findOne(21575);
        System.out.println(info);
    }

    @Test
    public void test4() {
        AlgUserFeaturesInfo info = algUserFeaturesInfoMapper.findById(21575);
        System.out.println(info);
    }

    @Test
    public void test3() throws IOException {
        long btime = System.currentTimeMillis();
        //    System.out.println(algUserFeaturesInfoDao.findAll().size());
        Page page = algUserFeaturesInfoDao.findAll(new PageRequest(0,1000));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
//        os.writeObject(list);
//        os.close();
//
//        System.out.println("字节:"+byteArrayOutputStream.size());
//
        long etime = System.currentTimeMillis();

        System.out.println(page.getTotalElements());

        System.out.println(etime - btime);


        btime = System.currentTimeMillis();
        //    System.out.println(algUserFeaturesInfoDao.findAll().size());

        page = algUserFeaturesInfoDao.findAll(new PageRequest(0,1000));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
//        os.writeObject(list);
//        os.close();
//
//        System.out.println("字节:"+byteArrayOutputStream.size());
//
        etime = System.currentTimeMillis();

      //  System.out.println(page.getTotalElements());

        System.out.println(etime - btime);




        //    System.out.println(algUserFeaturesInfoDao.findAll().size());
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
//        os.writeObject(list);
//        os.close();
//
//        System.out.println("字节:"+byteArrayOutputStream.size());
//
        //    System.out.println(algUserFeaturesInfoDao.findAll().size());
    }

    @Autowired
    UserDao userDao;

    @Test
    public void test6() {
//        long btime = System.currentTimeMillis();
//        Aggregation aggregation = Aggregation.newAggregation(AlgUserFeaturesInfo.class, match(Criteria.where("type").gte("1002")));
//        AggregationResults results = mongoTemplate.aggregate((TypedAggregation<?>) aggregation, AlgUserFeaturesInfo.class);
//        System.out.println(results.getMappedResults().size());
//        long etime = System.currentTimeMillis();
//        System.out.println(etime - btime);

       /* DBCursor cursor = mongoTemplate.getCollection("user").find();
        while (cursor.hasNext()){
            DBObject object = cursor.next();
            System.out.println(object.get("name"));
        }*/
//       userDao.save(new User(3L,"znn"));
//        System.out.println(userDao.findOne(3L).getName());
        userDao.deleteByName("znn");
        System.out.println(userDao.findByName("znn"));
    }
}
