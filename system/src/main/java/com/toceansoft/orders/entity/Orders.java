package com.toceansoft.orders.entity;

import com.toceansoft.common.entity.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单管理对象 t_orders
 *
 * @author hhh
 * @date Wed Sep 07 10:44:01 CST 2022
 */
@Data
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消费单id */
    private Long consumptionListId;

    /** 客户id */
    private Long clientId;

    /** 商品id */
    private Long goodsId;

    /** 商品量 */
    private Long quantity;

    /** 订单状态 */
    private String state;

    /** 销售员id */
    private Long salesmanId;

    /** 总额 */
    private Long totalAmount;

    /** 删除标签 */
    private String delLable;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("consumptionListId", getConsumptionListId())
            .append("clientId", getClientId())
            .append("goodsId", getGoodsId())
            .append("quantity", getQuantity())
            .append("state", getState())
            .append("salesmanId", getSalesmanId())
            .append("totalAmount", getTotalAmount())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("delLable", getDelLable())
            .toString();
    }
}
