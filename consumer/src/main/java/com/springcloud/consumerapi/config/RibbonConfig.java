package com.springcloud.consumerapi.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.springcloud.consumerapi.controller.ApiController;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 * @date 2021/1/11 21:15:00
 * @description Ribbon负载均衡策略配置
 */
@RibbonClient(name="server-api", configuration=ApiController.class)
public class RibbonConfig {

    /**
     * com.netflix.loadbalancer.RandomRule：从提供服务的实例中以随机的方式；
     * com.netflix.loadbalancer.RoundRobinRule：以线性轮询的方式，就是维护一个计数器，从提供服务的实例中按顺序选取，第一次选第一个，第二次选第二个，以此类推，到最后一个以后再从头来过；
     * com.netflix.loadbalancer.RetryRule：在RoundRobinRule的基础上添加重试机制，即在指定的重试时间内，反复使用线性轮询策略来选择可用实例；
     * com.netflix.loadbalancer.WeightedResponseTimeRule：对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择；
     * com.netflix.loadbalancer.BestAvailableRule：选择并发较小的实例；
     * com.netflix.loadbalancer.AvailabilityFilteringRule：先过滤掉故障实例，再选择并发较小的实例；
     * com.netflix.loadbalancer.ZoneAwareLoadBalancer：采用双重过滤，同时过滤不是同一区域的实例和故障实例，选择并发较小的实例。
     * @return
     */

    @Bean
    public IRule getRule(){
        return new RandomRule();
    }
}
