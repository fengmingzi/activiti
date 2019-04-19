package com.main.activity.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: fengguang xu
 * @Description: 用户角色关联实体类
 * @Date: 2019/4/18 16:30
 */
@Data
public class UserRole implements Serializable {

    /**用户id*/
    private Long uid;

    /**角色id*/
    private Long rid;
}
