package com.toceansoft.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统管理员
 */
@Data
public class Admin implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String nickname;
    private String phoneNumber;
    private String delFlag;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;

}
