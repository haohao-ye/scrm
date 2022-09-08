package com.toceansoft.salescount.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 销售统计对象 t_salescount
 *
 * @author zengqf
 * @date Thu Sep 08 15:08:48 CST 2022
 */
@Data
public class Salescount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodsId;

    /** 总销售额 */
    private Long totalSales;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("totalSales", getTotalSales())
            .toString();
    }
}
