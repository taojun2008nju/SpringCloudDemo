package com.springcloud.consumerapi.feign;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2020/9/28 19:10:00
 * @description 服务降级
 */
@Component
public class ServerApiFallBack implements ServerApiFeignClient {

    @Override
    public String testDb(String id) {
        return "Feign Fallback!";
    }
}
