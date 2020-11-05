package com.springcloud.serverapi.service.impl;

import com.springcloud.dao.entity.mongo.MongoDbTestEntity;
import com.springcloud.dao.repository.mongo.IMongoDbTestRepository;
import com.springcloud.serverapi.service.IMongoDbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020/10/31 17:19:00
 * @description TODO
 */
@Slf4j
@Service
public class MongoDbTestImplService implements IMongoDbTestService {

    @Autowired
    private IMongoDbTestRepository mongoDbTestRepository;

    @Override
    public void saveMongoDbEntity(MongoDbTestEntity demoEntity) {
        mongoDbTestRepository.saveMongoDbEntity(demoEntity);
    }

    @Override
    public void removeMongoDbEntity(Long id) {
        mongoDbTestRepository.removeMongoDbEntity(id);
    }

    @Override
    public void updateMongoDbEntity(MongoDbTestEntity demoEntity) {
        mongoDbTestRepository.updateMongoDbEntity(demoEntity);
    }

    @Override
    public MongoDbTestEntity findMongoDbEntityById(Long id) {
        MongoDbTestEntity mongoDbTestEntity = mongoDbTestRepository.findMongoDbEntityById(id);
        return mongoDbTestEntity;
    }
}
