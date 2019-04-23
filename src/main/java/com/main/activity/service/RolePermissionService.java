package com.main.activity.service;

import com.main.activity.common.BaseService;
import com.main.activity.common.utils.Response;
import com.main.activity.model.RolePermission;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色和权限关联service
 * @Date: 2019/4/19 14:01
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    /**
     * @author fengguang xu
     * @description 批量插入角色和权限数据
     * @date 2019/4/19 13:48
     * @param rid	角色id
     * @param pids	权限id集合
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> createBatch(Long rid, List<String> pids);

    /**
     * @author fengguang xu
     * @description 根据角色id删除角色和权限关联数据
     * @date 2019/4/19 13:49
     * @param rid	角色id
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> deleteByRid(Long rid);

    /**
     * @author fengguang xu
     * @description 根据权限id删除角色和权限的关联数据
     * @date 2019/4/19 13:54
     * @param pid	权限id
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> deleteByPid(Long pid);

    /**
     * @author fengguang xu
     * @description 角色和权限绑定
     * @date 2019/4/22 15:40
     * @param rid   角色id
     * @param pids  权限id集合
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> addPermission(Long rid, List<String> pids);

}
