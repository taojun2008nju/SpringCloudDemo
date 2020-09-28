package com.springcloud.consumerapi.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @date 2020/8/8 15:46:00
 * @description Api控制类
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
//    @LoadBalanced
    private RestTemplate restTemplate;

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


    @GetMapping("/consumer")
    public String test1() {
        return restTemplate.getForObject("http://zuul-nacos-server/api/helloNacos",String.class);
    }

    @GetMapping("/consumer2")
    public String test2() {
        return restTemplate.getForObject("http://server-api/api/testDb",String.class);
    }

    /**
     * 熔断之后执行的方法
     * @return
     */
    public String testFallback(){
        return "熔断--服务正忙，请求稍后再试！";
    }

    @HystrixCommand(fallbackMethod = "testFallback")
    @RequestMapping(value = "/testHystrix",method = RequestMethod.GET)
    public String testHystrix() {
        System.out.println("Method:testHystrix");
        return restTemplate.getForObject("http://server-api/api/testDb",String.class);
    }
}
