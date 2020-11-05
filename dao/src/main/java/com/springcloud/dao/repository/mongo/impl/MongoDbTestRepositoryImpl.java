package com.springcloud.dao.repository.mongo.impl;

import com.springcloud.dao.entity.mongo.MongoDbTestEntity;
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
    public void saveMongoDbEntity(MongoDbTestEntity demoEntity) {
        mongoTemplate.save(demoEntity);
    }

    @Override
    public void removeMongoDbEntity(Long id) {
        MongoDbTestEntity demoEntity = new MongoDbTestEntity();
        demoEntity.setId(id);
        mongoTemplate.remove(demoEntity);
    }

    @Override
    public void updateMongoDbEntity(MongoDbTestEntity demoEntity) {
        Query query = new Query(Criteria.where("id").is(demoEntity.getId()));

        Update update = new Update();
        update.set("title", demoEntity.getTitle());
        update.set("description", demoEntity.getDescription());
        update.set("by", demoEntity.getBy());
        update.set("url", demoEntity.getUrl());

        mongoTemplate.updateFirst(query, update, MongoDbTestEntity.class);
    }

    @Override
    public MongoDbTestEntity findMongoDbEntityById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        MongoDbTestEntity demoEntity = mongoTemplate.findOne(query, MongoDbTestEntity.class);
        return demoEntity;
    }
}
