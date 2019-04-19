package com.main.activity.controller;

import com.main.activity.common.utils.Response;
import com.main.activity.model.Role;
import com.main.activity.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fengguang xu
 * @Description: 角色接口
 * @Date: 2019/4/18 16:19
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/out/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * @author fengguang xu
     * @description 创建角色
     * @date 2019/4/18 16:24
     * @param role
     * @return com.main.activity.common.utils.Response<com.main.activity.model.Role>
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Role> create(Role role) {
        String name = role.getName();
        if (StringUtils.isEmpty(name)) {
            Response.fail("400", "名称不能为空");
        }
        role.setIsDeleted(0);
        Response<Role> roleResponse = roleService.create(role);
        if (!roleResponse.getIsSuccess()) {
            Response.fail("400", roleResponse.getMsg());
        }
        return Response.ok(roleResponse.getData());
    }

}
