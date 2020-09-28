package com.springcloud.consumerapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @date 2020/9/28 19:20:00
 * @description TODO
 */
@Component
@FeignClient(name = "server-api", fallback = ServerApiFallBack.class)
public interface ServerApiFeignClient {

    @RequestMapping(value="/api/testDb", method= RequestMethod.GET)
    String testDb(@RequestParam(required = false,name = "id") String id);
}
