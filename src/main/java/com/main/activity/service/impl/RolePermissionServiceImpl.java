package com.main.activity.service.impl;

import com.main.activity.common.Pager;
import com.main.activity.common.utils.Response;
import com.main.activity.dao.RolePermissionMapper;
import com.main.activity.model.RolePermission;
import com.main.activity.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色和权限关联service实现类
 * @Date: 2019/4/19 14:06
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Response<Boolean> createBatch(Long rid, List<Long> pids) {
        return Response.ok(rolePermissionMapper.createBatch(rid, pids));
    }

    @Override
    public Response<Boolean> deleteByRid(Long rid) {
        return Response.ok(rolePermissionMapper.deleteByRid(rid));
    }

    @Override
    public Response<Boolean> deleteByPid(Long pid) {
        return Response.ok(rolePermissionMapper.deleteByPid(pid));
    }

    @Override
    public Response<RolePermission> create(RolePermission rolePermission) {
        Boolean flag = rolePermissionMapper.create(rolePermission);
        if (flag) {
            return Response.ok(rolePermission);
        }
        return Response.fail("400", "创建角色与权限关联失败");
    }

    @Override
    public Response<RolePermission> update(RolePermission rolePermission) {
        return null;
    }

    @Override
    public Response<Boolean> deleteLogicalById(RolePermission rolePermission) {
        return null;
    }

    @Override
    public Response<Boolean> deleteById(RolePermission rolePermission) {
        return null;
    }

    @Override
    public Response<RolePermission> getOneById(Long id) {
        return null;
    }

    @Override
    public Response<RolePermission> getOneByCondition(RolePermission rolePermission) {
        return null;
    }

    @Override
    public Response<Long> countByCondition(RolePermission rolePermission) {
        return null;
    }

    @Override
    public Response<Pager<RolePermission>> getPageByCondition(Pager<RolePermission> pager, RolePermission rolePermission) {
        return null;
    }
}
