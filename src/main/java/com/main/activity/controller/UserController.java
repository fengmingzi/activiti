package com.main.activity.controller;

import com.main.activity.common.Pager;
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

    /**
     * @author fengguang xu
     * @description 更新用户信息
     * @date 2019/4/17 9:41
     * @param user
     * @return com.main.activity.common.utils.Response<com.main.activity.model.User>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response<User> update(User user) {
        //TODO 需验证如果为空时是否会更新字段为空
        Response<User> userResponse = userService.update(user);
        return Response.ok(userResponse.getData());
    }

    /**
     * @author fengguang xu
     * @description 查询用户
     * @date 2019/4/17 10:52
     * @param user
     * @return com.main.activity.common.utils.Response<com.main.activity.model.User>
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    public Response<User> findUser(User user) {
        Response<User> userResponse = userService.getOneByCondition(user);
        if (!userResponse.getIsSuccess()) {
            return Response.fail("400", "用户查询失败");
        }
        return Response.ok(userResponse.getData());
    }

    /**
     * @author fengguang xu
     * @description 分页查询
     * @date 2019/4/17 11:17
     * @param pager 分页数据
     * @param user  用户数据
     * @return com.main.activity.common.utils.Response<com.main.activity.common.Pager<com.main.activity.model.User>>
     */
    @RequestMapping(value = "/pager", method = RequestMethod.GET)
    public Response<Pager<User>> pager(Pager<User> pager, User user) {
        Response<Pager<User>> pagerResponse = userService.getPageByCondition(pager, user);
        if (!pagerResponse.getIsSuccess()) {
            Response.fail("400", "查询失败");
        }
        return Response.ok(pagerResponse.getData());
    }

    /**
     * @author fengguang xu
     * @description 逻辑删除
     * @date 2019/4/17 15:21
     * @param user
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    @RequestMapping(value = "/deleteLogical", method = RequestMethod.POST)
    public Response<Boolean> deleteLogical(User user) {
        return userService.deleteLogicalById(user);
    }

    /**
     * @author fengguang xu
     * @description 删除用户
     * @date 2019/4/17 16:03
     * @param user
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Response<Boolean> delete(User user) {
        return userService.deleteById(user);
    }

}
