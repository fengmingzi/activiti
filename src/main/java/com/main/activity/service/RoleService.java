package com.main.activity.service;

import com.main.activity.common.BaseService;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Role;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色service
 * @Date: 2019/4/18 15:45
 */
public interface RoleService extends BaseService<Role> {

    /**
     * @author fengguang xu
     * @description 根据用户id查询角色
     * @date 2019/4/19 17:17
     * @param uid	用户id
     * @return com.main.activity.common.utils.Response<java.util.List<com.main.activity.model.Role>>
     */
    Response<List<Role>> getRoleByUid(Long uid);
}
