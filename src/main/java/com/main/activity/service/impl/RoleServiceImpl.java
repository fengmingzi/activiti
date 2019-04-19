package com.main.activity.service.impl;

import com.main.activity.common.Pager;
import com.main.activity.common.utils.Response;
import com.main.activity.dao.RoleMapper;
import com.main.activity.model.Role;
import com.main.activity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色service实现类
 * @Date: 2019/4/18 15:47
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * @author fengguang xu
     * @description 创建角色
     * @date 2019/4/18 15:54
     * @param role	角色数据
     * @return com.main.activity.common.utils.Response<com.main.activity.model.Role>
     */
    @Override
    public Response<Role> create(Role role) {
        Boolean flag = roleMapper.create(role);
        if (flag) {
            return Response.ok(role);
        }
        return Response.fail("400", "角色创建失败", role);
    }

    @Override
    public Response<Role> update(Role role) {
        Boolean flag = roleMapper.update(role);
        if (flag) {
            return Response.ok(role);
        }
        return Response.fail("400", "角色更新失败", role);
    }

    @Override
    public Response<Boolean> deleteLogicalById(Role role) {
        Boolean flag = roleMapper.deleteLogicalById(role);
        if (flag) {
            return Response.ok(flag);
        }
        return Response.fail("400", "删除角色失败", flag);
    }

    @Override
    public Response<Boolean> deleteById(Role role) {
        Boolean flag = roleMapper.deleteById(role);
        if (flag) {
            return Response.ok(flag);
        }
        return Response.fail("400", "删除角色失败", flag);
    }

    @Override
    public Response<Role> getOneById(Long id) {
        Role role = roleMapper.getOneById(id);
        return Response.ok(role);
    }

    @Override
    public Response<Role> getOneByCondition(Role role) {
        return Response.ok(roleMapper.getOneByCondition(role));
    }

    @Override
    public Response<Long> countByCondition(Role role) {
        return Response.ok(roleMapper.countByCondition(role));
    }

    @Override
    public Response<Pager<Role>> getPageByCondition(Pager<Role> pager, Role role) {
        Long count = roleMapper.countByCondition(role);
        List<Role> roles = roleMapper.getPageByCondition(pager, role);
        pager.setTotalCount(count);
        pager.setRecords(roles);
        return Response.ok(pager);
    }

    @Override
    public Response<List<Role>> getRoleByUid(Long uid) {
        List<Role> roles = roleMapper.getRoleByUid(uid);
        return Response.ok(roles);
    }
}
