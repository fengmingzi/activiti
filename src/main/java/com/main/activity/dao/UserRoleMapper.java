package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.Role;
import com.main.activity.model.User;
import com.main.activity.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 用户角色关联关系mapper
 * @Date: 2019/4/18 17:24
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * @author fengguang xu
     * @description 批量插入用户与角色关联数据
     * @date 2019/4/18 17:34
     * @param uid	用户id
     * @param rids	角色id集合
     * @return java.lang.Boolean
     */
    Boolean createBatch(Long uid, List<Long> rids);

    /**
     * @author fengguang xu
     * @description 根据用户id删除用户与角色关联数据
     * @date 2019/4/19 9:34
     * @param uid 用户id
     * @return java.lang.Boolean
     */
    Boolean deleteByUid(Long uid);

    /**
     * @author fengguang xu
     * @description 根据角色id删除用户与角色的关联数据
     * @date 2019/4/19 9:35
     * @param rid 角色id
     * @return java.lang.Boolean
     */
    Boolean deleteByRid(Long rid);

}
