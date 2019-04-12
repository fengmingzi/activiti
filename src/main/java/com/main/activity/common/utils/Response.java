package com.main.activity.common.utils;

import java.io.Serializable;

/**
 * @author fengguang xu
 * @description 请求响应类
 * @date 2019/4/12 14:57
 * @return
 */
public class Response<T> implements Serializable {

  private static final long serialVersionUID = -3756093330406208742L;

  /**结果集*/
  private T data;

  /**返回码*/
  private String code;

  /**消息*/
  private String msg;

  /**成功失败标识*/
  private Boolean isSuccess;


  public Response() {
    this.code = "0";
    this.msg = "请求成功";
    this.setIsSuccess(true);
  }

  public Response(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public Boolean getIsSuccess() {
    return this.isSuccess;
  }

  public void setIsSuccess(Boolean success) {
    this.isSuccess = success;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public static <T> Response<T> ok(T data) {
    Response resp = new Response();
    resp.setCode("0");
    resp.setData(data);
    resp.setIsSuccess(true);
    return resp;
  }

  public static <T> Response<T> ok(String code, T data) {
    Response resp = new Response();
    resp.setCode(code);
    resp.setData(data);
    resp.setIsSuccess(true);
    return resp;
  }

  public static <T> Response<T> fail(String code, String error) {
    Response resp = new Response();
    resp.setCode(code);
    resp.setMsg(error);
    resp.setIsSuccess(false);
    return resp;
  }

  public static <T> Response<T> fail(String code, String error, T data) {
    Response resp = new Response();
    resp.setCode(code);
    resp.setMsg(error);
    resp.setData(data);
    resp.setIsSuccess(false);
    return resp;
  }
}
