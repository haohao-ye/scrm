package com.toceansoft.dept.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 部门对象 t_dept
 *
 * @author YeGuanYao
 * @date Tue Sep 06 16:19:00 CST 2022
 */
@Data
public class Dept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门id */
    private Long id;

    /** 部门名称 */
    private String deptName;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptName", getDeptName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
