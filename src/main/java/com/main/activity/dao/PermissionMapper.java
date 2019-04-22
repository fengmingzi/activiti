package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 权限Mapper
 * @Date: 2019/4/19 10:13
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * @author fengguang xu
     * @description 根据用户id查询权限
     * @date 2019/4/22 9:27
     * @param uid	用户id
     * @return java.util.List<com.main.activity.model.Permission>
     */
    List<Permission> getPermissionByUid(Long uid);
}
