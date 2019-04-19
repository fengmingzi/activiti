package com.main.activity.controller;

import com.main.activity.common.utils.Response;
import com.main.activity.model.Permission;
import com.main.activity.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * @author fengguang xu
     * @description 创建权限
     * @date 2019/4/19 10:58
     * @param permission
     * @return com.main.activity.common.utils.Response<com.main.activity.model.Permission>
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Permission> create(Permission permission) {
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



}
