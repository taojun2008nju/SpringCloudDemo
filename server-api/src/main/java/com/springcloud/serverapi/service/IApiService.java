package com.springcloud.serverapi.service;

import com.springcloud.dao.entity.TestEntity;

/**
 * @author Administrator
 * @date 2020/9/30 10:11:00
 * @description TODO
 */
public interface IApiService {

    TestEntity selectById(Long id);
}
