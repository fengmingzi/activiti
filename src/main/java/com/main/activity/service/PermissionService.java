package com.main.activity.service;

import com.main.activity.common.BaseService;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Permission;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 权限service
 * @Date: 2019/4/19 10:23
 */
public interface PermissionService extends BaseService<Permission> {

    /**
     * @author fengguang xu
     * @description 根据用户id查询权限
     * @date 2019/4/22 9:30
     * @param uid	用户id
     * @return com.main.activity.common.utils.Response<java.util.List<com.main.activity.model.Permission>>
     */
    Response<List<Permission>> getPermissionByUid(Long uid);
}
