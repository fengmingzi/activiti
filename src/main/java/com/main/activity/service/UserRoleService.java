package com.main.activity.service;

import com.main.activity.common.BaseService;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Role;
import com.main.activity.model.User;
import com.main.activity.model.UserRole;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 用户与角色关联service
 * @Date: 2019/4/19 9:10
 */
public interface UserRoleService extends BaseService<UserRole> {

    /**
     * @author fengguang xu
     * @description 批量插入用户与角色的关联数据
     * @date 2019/4/19 9:18
     * @param uid	用户id
     * @param rids	角色id集合
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> createBatch(Long uid, List<Long> rids);

    /**
     * @author fengguang xu
     * @description 根据用户id删除用户与角色关联数据
     * @date 2019/4/19 9:34
     * @param uid 用户id
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> deleteByUid(Long uid);

    /**
     * @author fengguang xu
     * @description 根据角色id删除用户与角色的关联数据
     * @date 2019/4/19 9:35
     * @param rid 角色id
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> deleteByRid(Long rid);

    /**
     * @author fengguang xu
     * @description 用户关联角色
     * @date 2019/4/22 15:11
     * @param uid	用户id
     * @param rids	角色id集合
     * @return com.main.activity.common.utils.Response<java.lang.Boolean>
     */
    Response<Boolean> addRole(Long uid, List<Long> rids);
}
