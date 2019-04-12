package com.main.activity.service.impl;

import com.main.activity.common.Pager;
import com.main.activity.common.utils.Response;
import com.main.activity.dao.UserMapper;
import com.main.activity.model.User;
import com.main.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 用户service实现类
 * @Date: 2019/4/12 15:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Response<User> create(User user) {
        Boolean flag = userMapper.create(user);
        if (flag) {
            return Response.ok(user);
        }
        return Response.fail("400", "用户创建失败", user);
    }

    @Override
    public Response<User> update(User user) {
        Boolean flag = userMapper.update(user);
        if (flag) {
            return Response.ok(user);
        }
        return Response.fail("400", "用户更新失败", user);
    }

    @Override
    public Response<Boolean> deleteLogicalById(User user) {
        return null;
    }

    @Override
    public Response<Boolean> deleteById(User user) {
        return null;
    }

    @Override
    public Response<User> getOneById(Long id) {
        User user = userMapper.getOneById(id);
        return Response.ok(user);
    }

    @Override
    public Response<User> getOneByCondition(User user) {
        return null;
    }

    @Override
    public Response<Long> countByCondition(User user) {
        return null;
    }

    @Override
    public Response<List<User>> getPageByCondition(Pager<User> pager, User user) {
        return null;
    }
}
