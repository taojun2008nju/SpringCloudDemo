package com.springcloud.dao.repository.mongo;

import com.springcloud.dao.entity.mongo.MongoDbTestEntity;

/**
 * @author Administrator
 * @date 2020/10/31 17:08:00
 * @description MongoDb测试仓库接口
 */
public interface IMongoDbTestRepository {

    void saveMongoDbEntity(MongoDbTestEntity demoEntity);

    void removeMongoDbEntity(Long id);

    void updateMongoDbEntity(MongoDbTestEntity demoEntity);

    MongoDbTestEntity findMongoDbEntityById(Long id);
}
