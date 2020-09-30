package com.springcloud.serverapi.repository;

import com.springcloud.dao.entity.TestEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @date 2020/9/30 15:05:00
 * @description TODO
 */
public interface ITestEntityRepository {

    TestEntity selectById(@Param(value = "id") Long id);
}
