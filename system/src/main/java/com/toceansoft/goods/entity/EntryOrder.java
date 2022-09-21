package com.toceansoft.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EntryOrder extends Goods{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long id;

    /** 商品名称 */
    private String name;

    /** 类型 */
    private String type;

    /** 总额 */
    private Double totalPrice;


    /** 标签 */
    private String lable;

    /** 品牌 */
    private String brand;

    /** 数量 */

    private Long quantity;

    /** 颜色 */
    private String color;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    private String creatBy;

    /** 删除标签(0是未删除，1是已删除) */
    private String delFlag;
}
