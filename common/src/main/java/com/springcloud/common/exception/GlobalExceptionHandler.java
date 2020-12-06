package com.springcloud.common.exception;

import com.springcloud.common.common.CommonException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @date 2020/9/30 15:24:00
 * @description 异常统一捕获处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map customException(Exception e) {
        if (e instanceof CommonException) {
            log.error("Exception: ", e);
            Map map = new HashMap();
            map.put("code", ((CommonException) e).getCode());
            map.put("message", ((CommonException) e).getMessage());
            map.put("data", null);
            return map;
        } else {
            log.error("Exception: ", e);
            Map map = new HashMap();
            map.put("code", "400");
            map.put("message", "error");
            map.put("data", null);
            return map;
        }
    }
}
