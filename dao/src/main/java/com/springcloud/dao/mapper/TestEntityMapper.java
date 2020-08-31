package com.springcloud.dao.mapper;

import com.springcloud.dao.entity.TestEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @date 2020/8/30 12:38:00
 * @description TODO
 */
public interface TestEntityMapper {

    TestEntity selectById(@Param(value = "id") Long id);
}
