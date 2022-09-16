package com.toceansoft.employee.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 员工管理对象 t_employee
 *
 * @author chan_juhin
 * @date Wed Sep 07 14:43:57 CST 2022
 */
@Data
public class Employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工工号 */
    private Long id;

    /** 名字 */
    private String name;

    /** 员工身份证 */
    private String idNum;

    /** 手机号码 */
    private String phoneNumber;

    /** 系统登录密码 */
    private String password;

    /** 员工部门 */
    private String deptName;

    /** 员工部门id */
    private Long deptId;

    /** 员工新密码 */
    private String newPassword;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("idNum", getIdNum())
            .append("phoneNumber", getPhoneNumber())
            .append("password", getPassword())
            .append("deptName", getDeptName())
            .append("deptId",getDeptId())
            .toString();
    }
}
