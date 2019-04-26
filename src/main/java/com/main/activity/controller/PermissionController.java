package com.main.activity.controller;

import com.google.common.base.Splitter;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Permission;
import com.main.activity.service.PermissionService;
import com.main.activity.service.RolePermissionService;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 权限相关接口
 * @Date: 2019/4/19 10:54
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/out/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * @author fengguang xu
     * @description 创建权限
     * @date 2019/4/19 10:58
     * @param permission
     * @return com.main.activity.common.utils.Response<com.main.activity.model.Permission>
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Permission> create(Permission permission, HttpServletRequest request, HttpServletResponse response) {
        String name = permission.getName();
        if (StringUtils.isEmpty(name)) {
            Response.fail("400", "名称不能为空");
        }
        permission.setIsDeleted(0);
        Response<Permission> permissionResponse = permissionService.create(permission);
        if (!permissionResponse.getIsSuccess()) {
            Response.fail("400", permissionResponse.getMsg());
        }
        return Response.ok(permissionResponse.getData());
    }

    /**
     * @author fengguang xu
     * @description 角色绑定权限
     * @date 2019/4/22 15:24
     * @param rid	角色id
     * @param pids	权限ids,逗号分割
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    public Response<Boolean> addPermission(Long rid, String pids) {

        if (rid == null) {
            return Response.fail("400", "角色id不能为空");
        }

        List<String> pidList = new ArrayList<>();
        if (!StringUtils.isEmpty(pids)) {
            pidList = Splitter.on(",").splitToList(pids);
        }
        try {
            Response<Boolean> res = rolePermissionService.addPermission(rid, pidList);
            return res;
        } catch (Exception e) {
            log.error("角色绑定权限异常：", e);
            return Response.fail("500", e.getMessage());
        }
    }



}
