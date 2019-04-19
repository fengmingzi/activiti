package com.main.activity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: fengguang xu
 * @Description: 角色权限关联实体类
 * @Date: 2019/4/19 11:14
 */
@Data
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -3761398743668930435L;

    /**角色id*/
    private Long rid;

    /**权限id*/
    private Long pid;

}
