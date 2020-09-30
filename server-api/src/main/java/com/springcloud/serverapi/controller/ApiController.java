package com.springcloud.serverapi.controller;

import com.springcloud.common.common.CommonResult;
import com.springcloud.dao.entity.TestEntity;
import com.springcloud.serverapi.service.IApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/8/8 15:46:00
 * @description Api控制类
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private IApiService apiService;

    @RequestMapping(value = {"/", "/index"})
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }

    @RequestMapping("/testDb")
    @ResponseBody
    public CommonResult testDb(@RequestParam(name = "id", defaultValue = "1") long id) {
        log.info("Method:testDb id:{}", id);
        TestEntity testEntity = apiService.selectById(id);
        CommonResult<TestEntity> commonResult = new CommonResult("0", "success", testEntity);
        return commonResult;
    }
}
