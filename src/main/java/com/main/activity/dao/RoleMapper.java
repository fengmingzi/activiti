package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色mapper
 * @Date: 2019/4/18 15:42
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @author fengguang xu
     * @description 根据用户id查询用户角色
     * @date 2019/4/19 17:09
     * @param uid	用户id
     * @return java.util.List<com.main.activity.model.Role>
     */
    List<Role> getRoleByUid(Long uid);
}
