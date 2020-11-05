package com.springcloud.serverapi.service;

import com.springcloud.dao.entity.mongo.MongoDbTestEntity;

/**
 * @author Administrator
 * @date 2020/10/31 17:18:00
 * @description TODO
 */
public interface IMongoDbTestService {

    void saveMongoDbEntity(MongoDbTestEntity demoEntity);

    void removeMongoDbEntity(Long id);

    void updateMongoDbEntity(MongoDbTestEntity demoEntity);

    MongoDbTestEntity findMongoDbEntityById(Long id);
}
