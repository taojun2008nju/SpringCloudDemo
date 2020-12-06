package com.springcloud.common.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2020/9/30 15:28:00
 * @description 通用异常
 */
@Data
@NoArgsConstructor
public class CommonException extends RuntimeException {

    private String code;

    private String message;

    public CommonException(String message) {
        this.message = message;
    }

    public CommonException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public CommonException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
