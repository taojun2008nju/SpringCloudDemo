package com.springcloud.serverapi.service.impl;

import com.springcloud.dao.entity.TestEntity;
import com.springcloud.serverapi.repository.ITestEntityRepository;
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
    public TestEntity selectById(Long id) {
        return testEntityRepository.selectById(id);
    }
}
