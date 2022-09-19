package com.toceansoft.web.record.controller;


import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.record.entity.Record;
import com.toceansoft.record.service.IRecordService;
import com.toceansoft.common.util.JWTUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 销售员沟通记录Controller
 *
 * @author chan_juhin
 * @date Mon Sep 19 10:39:15 CST 2022
 */
@RestController
@RequestMapping("/api/record/record")
public class RecordController
{
    @Autowired
    private IRecordService recordService;

    /**
     * 查询销售员沟通记录列表
     */
    @GetMapping("/list")
    public R list(Record record, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Record> list = recordService.selectRecordList(record);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取销售员沟通记录详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,recordService.selectRecordById(id));
    }

    /**
     * 新增销售员沟通记录
     */
    @PostMapping
    public R add(@RequestBody Record record,  HttpServletRequest req)
    {
        String token = req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        record.setCreateBy(username);
        int rows = recordService.insertRecord(record);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改销售员沟通记录
     */
    @PutMapping
    public R edit(@RequestBody Record record,  HttpServletRequest req)
    {
        String token = req.getHeader("X-Token");
        //从token中获得用户名
        String username = JWTUtils.getUsername(token);
        record.setCreateBy(username);
         int rows = recordService.updateRecord(record);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除销售员沟通记录
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        int rows = recordService.deleteRecordByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
