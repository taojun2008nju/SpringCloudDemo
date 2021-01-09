package com.springcloud.serverapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {
	DataSourceAutoConfiguration.class
})
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.springcloud")
@EnableElasticsearchRepositories(basePackages = "com.springcloud.dao")
public class ServerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApiApplication.class, args);
	}

}
