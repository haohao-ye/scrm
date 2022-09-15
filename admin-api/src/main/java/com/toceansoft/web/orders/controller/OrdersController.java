package com.toceansoft.web.orders.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;
import com.toceansoft.common.util.R;
import com.toceansoft.goods.entity.Goods;
import com.toceansoft.goods.service.IGoodsService;
import com.toceansoft.orders.entity.Orders;
import com.toceansoft.orders.service.IOrdersService;
import com.toceansoft.web.goods.controller.GoodsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单管理Controller
 * 
 * @author hhh
 * @date Wed Sep 07 10:44:01 CST 2022
 */
@RestController
@RequestMapping("/api/orders/orders")
public class OrdersController
{
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询订单管理列表
     */
    @GetMapping("/list")
    public R list(Orders orders, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Orders> list = ordersService.selectOrdersList(orders);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象

    }


    @GetMapping("/lists")
    public R list(Orders orders)
    {
//        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Orders> list = ordersService.selectOrdersList(orders);
//        PageInfo pageInfo = new PageInfo(list); //构建分页对象
//        return R.ok(20000, pageInfo);//返回分页对象
        return  R.ok(20000,list);
    }

    /**
     * 获取订单管理详细信息
     */
    @GetMapping(value = "/{consumptionListId}")
    public R getInfo(@PathVariable("consumptionListId") Long consumptionListId)
    {
        return R.ok(20000,ordersService.selectOrdersById(consumptionListId));
    }

    /**
     * 新增订单管理
     */
    @PostMapping
    public R add(HttpServletRequest req, @RequestBody Orders orders)
    {
        //1 获得requet请求
        //2 获得token
        String token =req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        //从redis缓存中读取当前登录用户
        Admin admin = (Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);

        orders.setCreateBy(admin.getUsername());

        int rows = ordersService.insertOrders(orders);



        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        Goods goods = goodsService.selectGoodsById(orders.getGoodsId());
        long number = goods.getInventory() - orders.getQuantity();
        goods.setInventory(number);
        goodsService.updateGoods(goods);
        return R.ok(20000, null);

    }

    /**
     * 修改订单管理
     */
    @PutMapping
    public R edit(@RequestBody Orders orders)
    {
         int rows = ordersService.updateOrders(orders);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除订单管理
     */
	@DeleteMapping("/{consumptionListIds}")
    public R remove(@PathVariable Long[] consumptionListIds)
    {
        int rows = ordersService.deleteOrdersByIds(consumptionListIds);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
