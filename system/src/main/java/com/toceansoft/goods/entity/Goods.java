package com.toceansoft.goods.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.toceansoft.common.entity.BaseEntity;

/**
<<<<<<< HEAD
 * 商品管理对象 t_goods
 *
 * @author ygy
 * @date Wed Sep 07 14:22:42 CST 2022
=======
 * 商品对象 t_goods
 *
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
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
    private Double price;


    /** 标签 */
    private String lable;

    /** 品牌 */
    private String brand;

    /** 库存 */

    private Long inventory;

    /** 颜色 */
    private String color;

    /** 折扣 */
    private Long activity_id;


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
            .append("activity_id", getActivity_id())
            .append("creatTime", getCreatTime())
            .append("creatBy", getCreatBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
