package com.toceansoft.goods.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
 * 商品对象 t_goods
 *
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
 */
@Data
public class Goods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long id;

    /** 名称 */
    private String name;

    /** 类型 */
    private String type;

    /** 价格 */
    private Long price;

    /** 标签 */
    private String lable;

    /** 品牌 */
    private String brand;

    /** 库存(bigint20 映射 long类型) */
    private Long inventory;

    /** 颜色 */
    private String color;

    /** 商品适用的折扣活动 */
    private int discount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    private String creatBy;

    /** 删除标签(0是未删除，1是已删除) */
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("price", getPrice())
            .append("lable", getLable())
            .append("brand", getBrand())
            .append("inventory", getInventory())
            .append("color", getColor())
            .append("discount", getDiscount())
            .append("creatTime", getCreatTime())
            .append("creatBy", getCreatBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
