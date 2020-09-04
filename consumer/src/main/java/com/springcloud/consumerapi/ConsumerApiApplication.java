package com.springcloud.consumerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.springcloud.consumerapi")
public class ConsumerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApiApplication.class, args);
	}

}
