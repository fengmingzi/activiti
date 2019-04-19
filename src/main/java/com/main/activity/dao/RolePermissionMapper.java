package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 角色和权限关联表mapper
 * @Date: 2019/4/19 13:41
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * @author fengguang xu
     * @description 批量插入角色和权限数据
     * @date 2019/4/19 13:48
     * @param rid	角色id
     * @param pids	权限id集合
     * @return java.lang.Boolean
     */
    Boolean createBatch(Long rid, List<Long> pids);

    /**
     * @author fengguang xu
     * @description 根据角色id删除角色和权限关联数据
     * @date 2019/4/19 13:49
     * @param rid	角色id
     * @return java.lang.Boolean
     */
    Boolean deleteByRid(Long rid);

    /**
     * @author fengguang xu
     * @description 根据权限id删除角色和权限的关联数据
     * @date 2019/4/19 13:54
     * @param pid	权限id
     * @return java.lang.Boolean
     */
    Boolean deleteByPid(Long pid);
}
