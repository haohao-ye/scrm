package com.toceansoft.inform.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * inform对象 t_inform
 *
 * @author chan_juhin
 * @date Wed Sep 07 10:39:38 CST 2022
 */
@Data
public class Inform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知id */
    private Long id;

    /** 通知标题 */
    private String infoTitle;

    /** 通知管理 */
    private String infoAdm;

    /** 通知销售 */
    private String infoSol;

    /** 通知仓管 */
    private String infoInv;

    /** 具体内容 */
    private String infoBody;

    /** 通知消失的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    /** 删除标签 */
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("infoTitle", getInfoTitle())
            .append("infoAdm", getInfoAdm())
            .append("infoSol", getInfoSol())
            .append("infoInv", getInfoInv())
            .append("infoBody", getInfoBody())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deadline", getDeadline())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
