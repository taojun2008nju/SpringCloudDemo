package com.springcloud.dao.repository.mongo.impl;

import com.springcloud.dao.entity.mongo.MongoDbTestEntitiy;
import com.springcloud.dao.repository.mongo.IMongoDbTestRepository;
import javax.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2020/10/31 17:09:00
 * @description MongoDb测试仓库实现
 */
@Repository
public class MongoDbTestRepositoryImpl implements IMongoDbTestRepository {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveDemo(MongoDbTestEntitiy demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeDemo(Long id) {
        mongoTemplate.remove(id);
    }

    @Override
    public void updateDemo(MongoDbTestEntitiy demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, MongoDbTestEntitiy.class);
    }

    @Override
    public MongoDbTestEntitiy findDemoById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        MongoDbTestEntitiy demoEntity = mongoTemplate.findOne(query, MongoDbTestEntitiy.class);
        return demoEntity;
    }
}
