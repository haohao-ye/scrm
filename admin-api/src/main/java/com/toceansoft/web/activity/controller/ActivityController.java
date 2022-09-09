package com.toceansoft.web.activity.controller;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.activity.entity.Activity;
import com.toceansoft.activity.service.ActivityService;

/**
 * 营销活动Controller
 * 
 * @author ygy
 * @date Thu Sep 08 10:20:59 CST 2022
 */
@RestController
@RequestMapping("/api/activity/activity")
public class ActivityController
{
    @Autowired
    private ActivityService activityService;

    /**
     * 查询营销活动列表
     */
    @GetMapping("/list")
    public R list(Activity activity, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Activity> list = activityService.selectActivityList(activity);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取营销活动详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,activityService.selectActivityById(id));
    }

    /**
     * 新增营销活动
     */
    @PostMapping
    public R add(@RequestBody Activity activity)
    {
        int rows = activityService.insertActivity(activity);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改营销活动
     */
    @PutMapping
    public R edit(@RequestBody Activity activity)
    {
         int rows = activityService.updateActivity(activity);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除营销活动
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        int rows = activityService.deleteActivityByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
