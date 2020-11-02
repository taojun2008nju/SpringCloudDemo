package com.springcloud.dao.repository.mongo;

import com.springcloud.dao.entity.mongo.MongoDbTestEntitiy;

/**
 * @author Administrator
 * @date 2020/10/31 17:08:00
 * @description MongoDb测试仓库接口
 */
public interface IMongoDbTestRepository {

    void saveDemo(MongoDbTestEntitiy demoEntity);

    void removeDemo(Long id);

    void updateDemo(MongoDbTestEntitiy demoEntity);

    MongoDbTestEntitiy findDemoById(Long id);
}
