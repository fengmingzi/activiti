package com.main.activity.model;

import com.main.activity.common.BaseModel;
import lombok.Data;

/**
 * @Author: fengguang xu
 * @Description: 权限实体类
 * @Date: 2019/4/19 10:03
 */
@Data
public class Permission extends BaseModel {

    private static final long serialVersionUID = 2777308963048089353L;

    private String name;

}
