package com.main.activity.model;

import com.main.activity.common.BaseModel;
import lombok.Data;

/**
 * @Author: fengguang xu
 * @Description: 用户实体类
 * @Date: 2019/4/12 9:56
 */
@Data
public class User extends BaseModel {

    private String name;
    private String password;

}
