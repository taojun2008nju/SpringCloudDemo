package com.springcloud.serverapi.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @date 2021/1/9 19:29:00
 * @description TODO
 */
@Slf4j
public class SentinelBlockHandler {

    /**
     * 限流自定义处理
     * @param e
     * @return
     */
    public static String indexBlockHandler(BlockException e) {
        log.error("Method:indexBlockHandler exception:", e);
        return new String("自定义限流信息");
    }
}
