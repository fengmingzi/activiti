package com.main.activity.service.impl;

import com.main.activity.common.Pager;
import com.main.activity.common.utils.Response;
import com.main.activity.dao.PermissionMapper;
import com.main.activity.model.Permission;
import com.main.activity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 权限service实现类
 * @Date: 2019/4/19 10:25
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Response<Permission> create(Permission permission) {
        Boolean flag = permissionMapper.create(permission);
        if (flag) {
            return Response.ok(permission);
        }
        return Response.fail("400", "创建权限失败");
    }

    @Override
    public Response<Permission> update(Permission permission) {
        Boolean flag = permissionMapper.update(permission);
        if (flag) {
            return Response.ok(permission);
        }
        return Response.fail("400", "权限更新失败");
    }

    @Override
    public Response<Boolean> deleteLogicalById(Permission permission) {
        //TODO 需要增加验证权限是否被使用，如果被使用不能删除
        Boolean flag = permissionMapper.deleteLogicalById(permission);
        if (flag) {
            return Response.ok(flag);
        }
        return Response.fail("400", "删除权限失败", flag);
    }

    @Override
    public Response<Boolean> deleteById(Permission permission) {
        //TODO 需要增加验证权限是否被使用，如果被使用不能删除
        Boolean flag = permissionMapper.deleteById(permission);
        if (flag) {
            return Response.ok(flag);
        }
        return Response.fail("400", "删除权限失败", flag);
    }

    @Override
    public Response<Permission> getOneById(Long id) {
        Permission permission = permissionMapper.getOneById(id);
        return Response.ok(permission);
    }

    @Override
    public Response<Permission> getOneByCondition(Permission permission) {
        return Response.ok(permissionMapper.getOneByCondition(permission));
    }

    @Override
    public Response<Long> countByCondition(Permission permission) {
        return Response.ok(permissionMapper.countByCondition(permission));
    }

    @Override
    public Response<Pager<Permission>> getPageByCondition(Pager<Permission> pager, Permission permission) {
        Long count = permissionMapper.countByCondition(permission);
        List<Permission> permissions = permissionMapper.getPageByCondition(pager, permission);
        pager.setTotalCount(count);
        pager.setRecords(permissions);
        return Response.ok(pager);
    }

    @Override
    public Response<List<Permission>> getPermissionByUid(Long uid) {
        List<Permission> permissions = permissionMapper.getPermissionByUid(uid);
        return Response.ok(permissions);
    }
}
