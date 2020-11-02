package com.springcloud.serverapi.service.impl;

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
}
