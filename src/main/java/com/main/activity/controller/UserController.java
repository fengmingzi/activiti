package com.main.activity.controller;

import com.main.activity.common.utils.Response;
import com.main.activity.model.User;
import com.main.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    /**
     * @author fengguang xu
     * @description 创建用户
     * @date 2019/4/16 17:07
     * @param user
     * @return com.main.activity.common.utils.Response<com.main.activity.model.User>
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<User> create(User user) {
        String name = user.getName();
        if (StringUtils.isEmpty(name)) {
            Response.fail("400", "名称不能为空");
        }
        Response<User> userResponse = userService.create(user);
        if (!userResponse.getIsSuccess()) {
            Response.fail("400", userResponse.getMsg());
        }
        return Response.ok(userResponse.getData());
    }

}
