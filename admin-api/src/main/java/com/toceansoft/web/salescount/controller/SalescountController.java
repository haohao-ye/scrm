package com.toceansoft.web.salescount.controller;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.salescount.entity.Salescount;
import com.toceansoft.salescount.service.ISalescountService;

import javax.servlet.http.HttpServletRequest;

/**
 * 销售统计Controller
 * 
 * @author hhh
 * @date Wed Sep 07 16:13:05 CST 2022
 */
@RestController
@RequestMapping("/api/salescount/salescount")
public class SalescountController
{
    @Autowired
    private ISalescountService salescountService;


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询销售统计列表
     */
    @GetMapping("/list")
    public R list(Salescount salescount, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Salescount> list = salescountService.selectSalescountList(salescount);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取销售统计详细信息
     */
    @GetMapping(value = "/{countId}")
    public R getInfo(@PathVariable("countId") Long countId)
    {
        return R.ok(20000,salescountService.selectSalescountById(countId));
    }

    /**
     * 新增销售统计
     */
    @PostMapping
    public R add(HttpServletRequest req, @RequestBody Salescount salescount)
    {

        //1 获得requet请求
        //2 获得token
        String token =req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        //从redis缓存中读取当前登录用户
        Admin admin = (Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);

        salescount.setCreateBy(admin.getUsername());

        int rows = salescountService.insertSalescount(salescount);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改销售统计
     */
    @PutMapping
    public R edit(@RequestBody Salescount salescount)
    {
         int rows = salescountService.updateSalescount(salescount);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除销售统计
     */
	@DeleteMapping("/{countIds}")
    public R remove(@PathVariable Long[] countIds)
    {
        int rows = salescountService.deleteSalescountByIds(countIds);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
