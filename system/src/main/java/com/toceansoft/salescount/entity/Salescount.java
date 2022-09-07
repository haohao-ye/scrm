package com.toceansoft.salescount.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 销售统计对象 t_salescount
 *
 * @author hhh
 * @date Wed Sep 07 16:38:16 CST 2022
 */
@Data
public class Salescount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 统计项id */
    private Long countId;

    /** 销售员id */
    private Long salesmanId;

    /** 已完成销售额 */
    private Long completedSales;

    /** 任务销售额 */
    private Long taskSales;

    /** 任务进程 */
    private String progress;

    /** 删除标签 */
    private String delLable;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("countId", getCountId())
            .append("salesmanId", getSalesmanId())
            .append(" completedSales", getCompletedSales())
            .append("taskSales", getTaskSales())
            .append("progress", getProgress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("delLable", getDelLable())
            .toString();
    }
}
