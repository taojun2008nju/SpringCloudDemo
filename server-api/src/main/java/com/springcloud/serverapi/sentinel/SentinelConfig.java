package com.springcloud.serverapi.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2021/1/9 12:27:00
 * @description Sentinel配置
 */
@Configuration
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    /*@PostConstruct
    private void initRules() throws Exception {
        FlowRule rule1 = new FlowRule();
        rule1.setResource("indexApiResource");
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setCount(1);   // 每秒调用最大次数为 1 次

        List<FlowRule> rules = new ArrayList<>();
        rules.add(rule1);

        // 将控制规则载入到 Sentinel
        com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager.loadRules(rules);
    }*/
}
