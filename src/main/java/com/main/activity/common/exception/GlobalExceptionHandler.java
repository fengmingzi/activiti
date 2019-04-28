package com.main.activity.common.exception;

import com.main.activity.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: fengguang xu
 * @Description: 全局异常处理
 * @Date: 2019/4/24 15:35
 */
@Slf4j
@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Response defaultErrorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getCause().getMessage());
    }

    /**
     * 拦截捕捉自定义异常 ServiceException.class
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServiceException.class)
    public Response serviceErrorHandler(ServiceException e) {
        log.error(e.getMessage(), e);
        return Response.fail(e.getCode() == null?"err500":e.getCode(), e.getMessage());
    }

    /**
     * @author fengguang xu
     * @description 捕捉shiro异常
     * @date 2019/4/24 15:47
     * @param e	
     * @return com.main.activity.common.utils.Response
     */
    @ExceptionHandler(ShiroException.class)
    public Response handleShiroException(Exception e) {
        return Response.fail("401", e.getMessage());
    }
}
