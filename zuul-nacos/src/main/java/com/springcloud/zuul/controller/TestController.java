package com.springcloud.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TestController {

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

    @GetMapping("/helloNacos")
    public String helloNacos(){
        return "你好，nacos！";
    }
}
