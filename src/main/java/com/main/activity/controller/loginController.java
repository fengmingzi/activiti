package com.main.activity.controller;

import com.main.activity.common.config.shiro.JWTToken;
import com.main.activity.common.utils.JWTUtil;
import com.main.activity.common.utils.Response;
import com.main.activity.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fengguang xu
 * @Description: 登陆相关接口
 * @Date: 2019/4/19 16:25
 */
@RestController
@RequestMapping(value = "/")
public class loginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    //退出的时候是get请求，主要是用于退出
    /**
     * @author fengguang xu
     * @description 1.退出登陆时跳转到登陆页面 2.未登录时跳转到登陆界面
     * @date 2019/4/22 16:26
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        //返回登陆界面
        return "login";
    }

    /**
     * @author fengguang xu
     * @description 调用登陆接口进行登陆验证
     * @date 2019/4/22 16:28
     * @param name	用户名
     * @param password	密码
     * @return java.lang.String
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<String> login(String name, String password) {
        //添加用户认证信息
        String token = JWTUtil.createToken(name);
        JWTToken jwtToken = new JWTToken(token);
        Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        //执行后调用MyShiroRealm中的doGetAuthenticationInfo方法
        subject.login(jwtToken);
        if (token != null) {
            return Response.ok(token);
        }
        return Response.fail("500", "生成token失败");
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "成功注销！";
    }

    /**
     * @author fengguang xu
     * @description 错误展示页面
     * @date 2019/4/22 13:52
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public String error() {
        return "error ok";
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/testShiro")
    public String create(){
        return "Create success!";
    }



}
