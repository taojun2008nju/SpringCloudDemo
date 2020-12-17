package com.springcloud.dao.repository.impl;

import com.springcloud.dao.entity.TestEntity;
import com.springcloud.dao.mapper.TestEntityMapper;
import com.springcloud.dao.repository.ITestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2020/9/30 15:05:00
 * @description TODO
 */
@Repository
public class TestEntityRepositoryImpl implements ITestEntityRepository {

    @Autowired
    private TestEntityMapper testEntityMapper;

    @Override
//    @Cacheable(value = "testEntity", key = "#id")
    public TestEntity selectById(Long id) {
        return testEntityMapper.selectById(id);
    }
}
