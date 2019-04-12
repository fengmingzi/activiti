package com.main.activity.dao;

import com.main.activity.common.BaseMapper;
import com.main.activity.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: fengguang xu
 * @Description: 用户Mapper类
 * @Date: 2019/4/12 14:38
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
