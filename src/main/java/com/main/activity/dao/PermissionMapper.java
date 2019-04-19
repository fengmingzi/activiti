package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: fengguang xu
 * @Description: 权限Mapper
 * @Date: 2019/4/19 10:13
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
