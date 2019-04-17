package com.main.activity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: fengguang xu
 * @Description: 基础实体类
 * @Date: 2019/4/12 10:01
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -4323072392687879053L;
    /**主键id*/
    private Long id;

    /**创建人*/
    private String creator;

    /**修改人*/
    private String modifyUser;

    /**创建时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**更新时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    /**是否删除标识，0：不删除(默认)，1：删除*/
    private Integer isDeleted;

}
