package com.main.activity.model;

import com.main.activity.common.BaseModel;
import lombok.Data;

/**
 * @Author: fengguang xu
 * @Description: 用户角色实体类
 * @Date: 2019/4/18 15:32
 */
@Data
public class Role extends BaseModel {
    private static final long serialVersionUID = -2325348325745948391L;

    private String name;

}
