package com.springcloud.serverapi.controller;

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
//@EnableFeignClients
@Slf4j
public class ApiController {

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


    @Autowired
    private IApiService apiService;

    @RequestMapping("/testDb")
    @ResponseBody
    public String testDb() {
        log.info("Method:testDb");
        TestEntity testEntity = apiService.selectById(1L);
        return testEntity.getName();
    }
}
