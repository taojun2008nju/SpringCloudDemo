package com.springcloud.gateway.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author Administrator
 * @date 2021/2/18 10:33:00
 * @description 过滤器模型
 */
@Data
public class GatewayFilterDefinition implements Serializable {

    //Filter Name
    private String name;
    //对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}
