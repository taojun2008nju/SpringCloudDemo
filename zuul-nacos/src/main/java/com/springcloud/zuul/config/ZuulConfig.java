package com.springcloud.zuul.config;

import com.springcloud.zuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }
}
