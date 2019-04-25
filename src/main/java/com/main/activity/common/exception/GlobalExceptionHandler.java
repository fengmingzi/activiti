package com.main.activity.common.exception;

import com.main.activity.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
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
    public Response defaultErrorHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getCause().getMessage());
    }

    /**
     * 拦截捕捉自定义异常 CubaException.class
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServiceException.class)
    public Response cubaErrorHandler(ServiceException ex) {
        log.error(ex.getMessage(), ex);
        return Response.fail(ex.getCode() == null?"err500":ex.getCode(), ex.getMessage());
    }

    /**
     * @author fengguang xu
     * @description 捕捉shiro异常
     * @date 2019/4/24 15:47
     * @param e	
     * @return com.main.activity.common.utils.Response
     */
    @ExceptionHandler(AccountException.class)
    public Response handleShiroException(Exception e) {
        return Response.fail("400", e.getMessage());

    }
}
