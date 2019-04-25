package com.main.activity.common.exception;

import lombok.Data;

/**
 * @Author: fengguang xu
 * @Description: 自定义运行时异常
 * @Date: 2019/4/24 16:25
 */
@Data
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
