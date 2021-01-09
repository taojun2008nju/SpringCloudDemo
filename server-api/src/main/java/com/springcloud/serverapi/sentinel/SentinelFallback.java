package com.springcloud.serverapi.sentinel;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2021/1/9 19:31:00
 * @description TODO
 */
@Slf4j
public class SentinelFallback {

    /**
     * 降级处理
     * @param e
     * @return
     */
    public static String indexFallback(Throwable e){
        log.error("Method:indexFallback exception:", e);
        return "index fallback";
    }
}
