package com.springcloud.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springcloud.oauth2")
@MapperScan("com.springcloud.oauth2.mapper")
@Slf4j
public class OAuth2AuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2AuthorizationApplication.class, args);
	}

}
