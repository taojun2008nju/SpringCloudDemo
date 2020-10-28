package com.springcloud.serverapi.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/10/27 21:15:00
 * @description ElasticSearchRestClientConfig类
 */
@Slf4j
@Configuration
public class ElasticSearchRestClientConfig {

    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

    /**
     * es服务ip
     */
    @Value("${elasticsearch.ip}")
    private String esIp;

    /**
     * es服务端口
     */
    @Value("${elasticsearch.httpPort}")
    private Integer esPort;

    @Bean
    public RestClientBuilder restClientBuilder() {
        // 集群方式
        /*HttpHost[] hosts = Arrays.stream(ipAddress)
            .map(this::makeHttpHost)
            .filter(Objects::nonNull)
            .toArray(HttpHost[]::new);
        log.debug("hosts:{}", Arrays.toString(hosts));
        return RestClient.builder(hosts);*/
        // 单机方式
        return RestClient.builder(new HttpHost(esIp, esPort));
    }


    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
//        restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(restClientBuilder);
    }


    private HttpHost makeHttpHost(String s) {
        assert StringUtils.isNotEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port, HTTP_SCHEME);
        } else {
            return null;
        }
    }
}
