package com.toceansoft.web.goods.controller;

import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.goods.entity.Goods;
import com.toceansoft.goods.service.IGoodsService;

/**
 * 商品Controller
 * 
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
 */
@RestController
@RequestMapping("/api/goods/goods")
public class GoodsController
{
    @Autowired
    private IGoodsService goodsService;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    public R list(Goods goods, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Goods> list = goodsService.selectGoodsList(goods);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取商品详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,goodsService.selectGoodsById(id));
    }

    /**
     * 新增商品
     */
    @PostMapping
    public R add(@RequestBody Goods goods)
    {
        int rows = goodsService.insertGoods(goods);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改商品
     */
    @PutMapping
    public R edit(@RequestBody Goods goods)
    {
         int rows = goodsService.updateGoods(goods);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除商品
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        int rows = goodsService.deleteGoodsByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
