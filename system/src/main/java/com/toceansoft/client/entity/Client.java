package com.toceansoft.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 【请填写功能名称】对象 t_client
 *
 * @author zengqf
 * @date Wed Sep 07 09:51:06 CST 2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户id */
    private Long id;

    /** 客户名称 */
    private String clientName;

    /** 客户地址 */
    private String address;

    /** 客户联系方式 */
    private String phoneNumber;

    /** 客户标签 */
    private String clientLevel;

    /** 客户组别 */
    private Long clientGroup;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 创建人 */
    private String creator;

    /** 更新人 */
    private String updatePerson;

    /** 删除标签 */
    private String delLabel;

    /** 联系标签 */
    private String contactLabel;

    /** 客户组别 */
    private String clientGroupName;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clientName", getClientName())
                .append("address", getAddress())
                .append("phoneNumber", getPhoneNumber())
                .append("clientLevel", getClientLevel())
                .append("clientGroup", getClientGroup())
                .append("createTime", getCreateTime())
                .append("creator", getCreator())
                .append("updateTime", getUpdateTime())
                .append("updatePerson", getUpdatePerson())
                .append("delLabel", getDelLabel())
                .append("contactLabel", getContactLabel())
                .toString();
    }
}
