package com.springcloud.common.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @date 2020/9/30 15:24:00
 * @description TODO
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map customException(Exception e) {
        Map map = new HashMap();
        map.put("code", "400");
        map.put("message", "error");
        map.put("data", null);
        return map;
    }
}
