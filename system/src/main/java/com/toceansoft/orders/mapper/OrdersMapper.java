package com.toceansoft.orders.mapper;

import com.toceansoft.orders.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单管理Mapper接口
 * 
 * @author hhh
 * @date Wed Sep 07 10:44:01 CST 2022
 */
 @Mapper
public interface OrdersMapper 
{
    /**
     * 查询订单管理
     * 
     * @param consumptionListId 订单管理ID
     * @return 订单管理
     */
    public Orders selectOrdersById(Long consumptionListId);

    /**
     * 查询订单管理列表
     * 
     * @param orders 订单管理
     * @return 订单管理集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增订单管理
     * 
     * @param orders 订单管理
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改订单管理
     * 
     * @param orders 订单管理
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 删除订单管理
     * 
     * @param consumptionListId 订单管理ID
     * @return 结果
     */
    public int deleteOrdersById(Long consumptionListId);

    /**
     * 批量删除订单管理
     * 
     * @param consumptionListIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] consumptionListIds);
}
