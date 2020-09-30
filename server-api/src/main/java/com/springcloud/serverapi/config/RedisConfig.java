package com.springcloud.serverapi.config;

import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.PoolException;

/**
 * @author Administrator
 * @date 2020/9/30 10:05:00
 * @description redis配置
 */
@Slf4j
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    /**
     * redis统一异常处理
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                if (e instanceof RedisConnectionFailureException) {
                    log.error("Method:handleCacheGetError redis has lose connection:", e);
                    return;
                } else if (e instanceof PoolException) {
                    log.error("Method:handleCacheGetError redis has lose connection:", e);
                    return;
                } else if (e instanceof RedisException) {
                    log.error("Method:handleCacheGetError redis has lose connection:", e);
                    return;
                } else {
                    log.error("Method:handleCacheGetError error", e);
                    return;
                }
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                if (e instanceof RedisConnectionFailureException) {
                    log.error("Method:handleCachePutError redis has lose connection:", e);
                    return;
                } else if (e instanceof PoolException) {
                    log.error("Method:handleCachePutError redis has lose connection:", e);
                    return;
                } else if (e instanceof RedisException) {
                    log.error("Method:handleCachePutError redis has lose connection:", e);
                    return;
                }
                log.error("Method:handleCachePutError error", e);
                return;
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                if (e instanceof RedisConnectionFailureException) {
                    log.error("Method:handleCacheEvictError redis has lose connection:", e);
                    return;
                } else if (e instanceof PoolException) {
                    log.error("Method:handleCacheEvictError redis has lose connection:", e);
                    return;
                } else if (e instanceof RedisException) {
                    log.error("Method:handleCacheEvictError redis has lose connection:", e);
                    return;
                }
                log.error("Method:handleCacheEvictError error", e);
                return;
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                if (e instanceof RedisConnectionFailureException) {
                    log.error("Method:handleCacheClearError redis has lose connection:", e);
                    return;
                } else if (e instanceof PoolException) {
                    log.error("Method:handleCacheClearError redis has lose connection:", e);
                    return;
                } else if (e instanceof RedisException) {
                    log.error("Method:handleCacheClearError redis has lose connection:", e);
                    return;
                }
                log.error("Method:handleCacheClearError error", e);
                return;
            }
        };
        return cacheErrorHandler;
    }
}
