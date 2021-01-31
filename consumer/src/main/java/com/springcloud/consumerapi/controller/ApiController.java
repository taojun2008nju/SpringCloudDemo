package com.springcloud.consumerapi.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.consumerapi.feign.ServerApiFeignClient;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("Method:test2 begin");
        return restTemplate.getForObject("http://server-api/api/testDb",String.class);
    }

    /**
     * 熔断之后执行的方法
     * @return
     */
    public String testFallback(String id){
        return "熔断--服务正忙，请求稍后再试！";
    }

    @HystrixCommand(fallbackMethod = "testFallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"),
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
        },
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
        }, ignoreExceptions = NullPointerException.class)
    @RequestMapping(value = "/testHystrix",method = RequestMethod.GET)
    public String testHystrix(String id) {
        System.out.println("Method:testHystrix");
        if (id.equalsIgnoreCase("1")) {
            throw new NullPointerException();
        } else {
            return restTemplate.getForObject("http://server-api/api/testDb",String.class);
        }
    }

    @Autowired
    private ServerApiFeignClient serverApiFeignClient;

    @RequestMapping(value = "/feign/testDb", method = RequestMethod.GET)
    public String findById(String id) {
        return this.serverApiFeignClient.testDb(id);
    }
}
