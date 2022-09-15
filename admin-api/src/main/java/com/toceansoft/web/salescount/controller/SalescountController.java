package com.toceansoft.web.salescount.controller;


import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.salescount.entity.Salescount;
import com.toceansoft.salescount.service.ISalescountService;
import com.toceansoft.common.util.JWTUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 销售统计Controller
 *
 * @author zengqf
 * @date Thu Sep 08 15:08:48 CST 2022
 */
@RestController
@RequestMapping("/api/salescount/salescount")
public class SalescountController
{
    @Autowired
    private ISalescountService salescountService;

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
    @GetMapping(value = "/{goodsId}")
    public R getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return R.ok(20000,salescountService.selectSalescountById(goodsId));
    }

    /**
     * 新增销售统计
     */
    @PostMapping
    public R add(@RequestBody Salescount salescount,  HttpServletRequest req)
    {
        String token = req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        salescount.setCreateBy(username);
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
    public R edit(@RequestBody Salescount salescount,  HttpServletRequest req)
    {
        String token = req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        salescount.setCreateBy(username);
         int rows = salescountService.updateSalescount(salescount);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除销售统计
     */
	@DeleteMapping("/{goodsIds}")
    public R remove(@PathVariable Long[] goodsIds)
    {
        int rows = salescountService.deleteSalescountByIds(goodsIds);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
