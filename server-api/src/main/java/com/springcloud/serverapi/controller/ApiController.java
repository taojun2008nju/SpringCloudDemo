package com.springcloud.serverapi.controller;

import com.springcloud.dao.entity.TestEntity;
import com.springcloud.dao.mapper.TestEntityMapper;
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
    private TestEntityMapper testEntityMapper;

    @RequestMapping("/testDb")
    @ResponseBody
    public String testDb() {
        log.info("Method:testDb");
        TestEntity testEntity = testEntityMapper.selectById(1L);
        return testEntity.getName();
    }
}
