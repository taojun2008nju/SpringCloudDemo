package com.springcloud.serverapi.service.impl;

import com.springcloud.dao.entity.TestEntity;
import com.springcloud.dao.repository.ITestEntityRepository;
import com.springcloud.serverapi.annotation.DataSource;
import com.springcloud.serverapi.enums.DataSourceType;
import com.springcloud.serverapi.service.IApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020/9/30 10:12:00
 * @description TODO
 */
@Slf4j
@Service
public class ApiServiceImpl implements IApiService {

    @Autowired
    private ITestEntityRepository testEntityRepository;

    @Override
    @DataSource(value = DataSourceType.MASTER)
    public TestEntity selectById(Long id) {
        return testEntityRepository.selectById(id);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public TestEntity selectSlaveById(Long id) {
        return testEntityRepository.selectById(id);
    }
}
