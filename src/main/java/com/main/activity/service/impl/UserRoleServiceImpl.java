package com.main.activity.service.impl;

import com.main.activity.common.Pager;
import com.main.activity.common.utils.Response;
import com.main.activity.dao.UserRoleMapper;
import com.main.activity.model.UserRole;
import com.main.activity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 用户与角色关联service实现类
 * @Date: 2019/4/19 9:19
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Response<Boolean> createBatch(Long uid, List<Long> rids) {
        return Response.ok(userRoleMapper.createBatch(uid, rids));
    }

    @Override
    public Response<Boolean> deleteByUid(Long uid) {
        return Response.ok(userRoleMapper.deleteByUid(uid));
    }

    @Override
    public Response<Boolean> deleteByRid(Long rid) {
        return Response.ok(userRoleMapper.deleteByRid(rid));
    }

    @Override
    public Response<UserRole> create(UserRole userRole) {
        Boolean flag = userRoleMapper.create(userRole);
        if (flag) {
            return Response.ok(userRole);
        }
        return Response.fail("400", "创建用户与角色关联失败");
    }

    @Override
    public Response<UserRole> update(UserRole userRole) {
        return null;
    }

    @Override
    public Response<Boolean> deleteLogicalById(UserRole userRole) {
        return null;
    }

    @Override
    public Response<Boolean> deleteById(UserRole userRole) {
        return null;
    }

    @Override
    public Response<UserRole> getOneById(Long id) {
        return null;
    }

    @Override
    public Response<UserRole> getOneByCondition(UserRole userRole) {
        return null;
    }

    @Override
    public Response<Long> countByCondition(UserRole userRole) {
        return null;
    }

    @Override
    public Response<Pager<UserRole>> getPageByCondition(Pager<UserRole> pager, UserRole userRole) {
        return null;
    }

    /**
     * @author fengguang xu
     * @description 用户关联角色
     * @date 2019/4/22 14:42
     * @param uid   用户id
     * @param rids  角色id集合
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    @Transactional
    public Response<Boolean> addRole(Long uid, List<Long> rids) {
        //删除原用户与角色关联关系
        userRoleMapper.deleteByUid(uid);
        //保存用户与角色关联关系
        if (rids != null && rids.size() > 0) {
            userRoleMapper.createBatch(uid, rids);
        }
        return Response.ok(true);
    }

}
