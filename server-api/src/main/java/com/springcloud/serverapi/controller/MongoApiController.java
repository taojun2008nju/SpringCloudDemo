package com.springcloud.serverapi.controller;

import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.mongo.MongoDbTestEntity;
import com.springcloud.serverapi.service.IMongoDbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/10/31 17:18:00
 * @description mongo控制层
 */
@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoApiController {

    @Autowired
    private IMongoDbTestService mongoDbTestService;

    @RequestMapping("/saveMongoDbEntity")
    public CommonResult saveMongoDbEntity(@RequestBody MongoDbTestEntity demoEntity) {
        mongoDbTestService.saveMongoDbEntity(demoEntity);
        CommonResult commonResult = new CommonResult("200", "更新成功", null);
        return commonResult;
    }

    @RequestMapping("/updateMongoDbEntity")
    public CommonResult updateMongoDbEntity(@RequestBody MongoDbTestEntity demoEntity) {
        mongoDbTestService.updateMongoDbEntity(demoEntity);
        CommonResult commonResult = new CommonResult("200", "更新成功", null);
        return commonResult;
    }

    @RequestMapping("/removeMongoDbEntity")
    public CommonResult removeMongoDbEntity(@RequestParam(value = "id") long id) {
        mongoDbTestService.removeMongoDbEntity(id);
        CommonResult commonResult = new CommonResult("200", "更新成功", null);
        return commonResult;
    }


    @RequestMapping("/findMongoDbEntityById")
    public CommonResult findMongoDbEntityById(@RequestParam(value = "id") Long id) {
        MongoDbTestEntity mongoDbTestEntity = mongoDbTestService.findMongoDbEntityById(id);
        CommonResult commonResult = new CommonResult("200", "更新成功", mongoDbTestEntity);
        return commonResult;
    }
}
