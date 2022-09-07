package com.toceansoft.web.goods.controller;

import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;
import com.toceansoft.dept.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.goods.entity.Goods;
import com.toceansoft.goods.service.IGoodsService;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品管理Controller
 * 
 * @author ygy
 * @date Wed Sep 07 14:22:42 CST 2022
 */
@RestController
@RequestMapping("/api/goods/goods")
public class GoodsController
{
    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询商品管理列表
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
     * 获取商品管理详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,goodsService.selectGoodsById(id));
    }

    /**
     * 新增商品管理
     */
    @PostMapping
    public R add(@RequestBody Goods goods, HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        goods.setCreatBy(admin.getUsername());
        goods.setCreatTime(new Date());
        goods.setUpdateTime(new Date());
        Goods goods1=new Goods();
        goods1.setName(goods.getName());
        if(null!=goods&&goodsService.selectGoodsList(goods1).size()>0){
            return R.fail(0,"已存在");
        }
        int rows = goodsService.insertGoods(goods);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改商品管理
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
     * 删除商品管理
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
