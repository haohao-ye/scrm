package com.toceansoft.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private String newPassword;

    private String role;  // 角色
    private Long deptId;
}
