package com.main.activity.controller;

import com.main.activity.model.User;
import com.main.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fengguang xu
 * @Description: 用户控制器
 * @Date: 2019/4/12 15:30
 */
@RestController
@RequestMapping(value = "/api/out/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(User user) {
        userService.create(user);
    }

}
