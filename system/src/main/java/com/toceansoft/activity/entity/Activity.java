package com.toceansoft.activity.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 营销活动对象 t_activity
 *
 * @author ygy
 * @date Thu Sep 08 10:20:59 CST 2022
 */
@Data
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商业活动id */
    private Long id;

    /** 活动名称 */
    private String name;

    /** 活动类别 */
    private String type;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 活动负责人 */
    private String leaderId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    private String creatBy;

    /** 删除标签 */
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("endTime", getEndTime())
            .append("startTime", getStartTime())
            .append("leaderId", getLeaderId())
            .append("creatTime", getCreatTime())
            .append("creatBy", getCreatBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
