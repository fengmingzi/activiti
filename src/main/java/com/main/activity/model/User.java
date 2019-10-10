package com.main.activity.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 15227
 * @Description: //TODO
 * @Date: 2019/10/10 14:15
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -455009773992473786L;

    private Long id;
    private String name;
    private String password;
    private String creator;
    private Date createDate;
    private String modifyUser;
    private Date modifyDate;
    private Integer isDeleted;
}
