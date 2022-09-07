package com.toceansoft.orders.service.impl;

import com.toceansoft.common.util.DateUtils;
import com.toceansoft.orders.entity.Orders;
import com.toceansoft.orders.mapper.OrdersMapper;
import com.toceansoft.orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理Service业务层处理
 * 
 * @author hhh
 * @date Wed Sep 07 10:44:01 CST 2022
 */
@Service
public class OrdersServiceImpl implements IOrdersService 
{
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询订单管理
     * 
     * @param consumptionListId 订单管理ID
     * @return 订单管理
     */
    @Override
    public Orders selectOrdersById(Long consumptionListId)
    {
        return ordersMapper.selectOrdersById(consumptionListId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param orders 订单管理
     * @return 订单管理
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders)
    {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 新增订单管理
     * 
     * @param orders 订单管理
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
        orders.setCreateTime(DateUtils.getNowDate());
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 修改订单管理
     * 
     * @param orders 订单管理
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 批量删除订单管理
     * 
     * @param consumptionListIds 需要删除的订单管理ID
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(Long[] consumptionListIds)
    {
        return ordersMapper.deleteOrdersByIds(consumptionListIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param consumptionListId 订单管理ID
     * @return 结果
     */
    @Override
    public int deleteOrdersById(Long consumptionListId)
    {
        return ordersMapper.deleteOrdersById(consumptionListId);
    }
}
