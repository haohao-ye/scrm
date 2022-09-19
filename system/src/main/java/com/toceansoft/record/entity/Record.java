package com.toceansoft.record.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 销售员沟通记录对象 t_record
 *
 * @author chan_juhin
 * @date Mon Sep 19 10:39:15 CST 2022
 */
@Data
public class Record extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录id */
    private Long id;

    /** 客户id */
    private Long clientid;

    /** 销售员id */
    private Long employeeid;

    /** 沟通方式：短信、电话 */
    private String mode;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientid", getClientid())
            .append("employeeid", getEmployeeid())
            .append("mode", getMode())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
