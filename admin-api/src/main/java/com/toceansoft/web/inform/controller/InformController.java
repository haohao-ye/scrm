package com.toceansoft.web.inform.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.inform.entity.Inform;
import com.toceansoft.inform.service.IInformService;

import javax.servlet.http.HttpServletRequest;

/**
 * 通知Controller
 * 
 * @author chan_juhin
 * @date Wed Sep 07 09:37:22 CST 2022
 */
@RestController
@RequestMapping("/api/inform/inform")
public class InformController
{
    @Autowired
    private IInformService informService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询通知列表
     */
    @GetMapping("/list")
    public R list(Inform inform, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Inform> list = informService.selectInformList(inform);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 小程序端查询对应的通知
     * @param inform
     * @return
     */
    @GetMapping("/lists")
    public R lists(Inform inform)
    {
        inform.setInfoSol("是");
        System.out.println(inform);
        List<Inform> list = informService.selectInformList(inform);
        System.out.println(list);
        return R.ok(20000, list);
    }

    /**
     * 获取通知详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,informService.selectInformById(id));
    }

    /**
     * 新增通知
     */
    @PostMapping
    public R add(@RequestBody Inform inform, HttpServletRequest req)
    {

        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        inform.setUpdateBy(admin.getUsername());
        inform.setCreateBy(admin.getUsername());
        int rows = informService.insertInform(inform);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改通知
     */
    @PutMapping
    public R edit(@RequestBody Inform inform,HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        inform.setUpdateBy(admin.getUsername());
         int rows = informService.updateInform(inform);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除通知
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids,HttpServletRequest req)
    {

        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        for (Long id :ids
        ) {
            Inform inform = informService.selectInformById(id);
            inform.setUpdateBy(admin.getUsername());
        }
        int rows = informService.deleteInformByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }

        return R.ok(20000, null);
    }

}
