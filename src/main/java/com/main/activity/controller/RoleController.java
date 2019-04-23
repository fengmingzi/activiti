package com.main.activity.controller;

import com.google.common.base.Splitter;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Role;
import com.main.activity.service.RoleService;
import com.main.activity.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private UserRoleService userRoleService;

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

    /**
     * @author fengguang xu
     * @description 给用户添加角色
     * @date 2019/4/22 14:17
     * @param uid	用户id
     * @param rids  角色ids,逗号分隔
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Response<Boolean> addRole(Long uid, String rids) {

        if (uid == null) {
            return Response.fail("400", "用户id不能为空");
        }

        List<String> ridList = new ArrayList<>();
        if (!StringUtils.isEmpty(rids)) {
            ridList = Splitter.on(",").splitToList(rids);
        }

        try {
            Response<Boolean> res = userRoleService.addRole(uid, ridList);
            return res;
        } catch (Exception e) {
            log.error("给用户绑定角色异常：", e);
            return Response.fail("400", e.getMessage());
        }
    }


}
