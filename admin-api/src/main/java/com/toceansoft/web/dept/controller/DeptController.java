package com.toceansoft.web.dept.controller;

import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.dept.entity.Dept;
import com.toceansoft.dept.service.IDeptService;

/**
 * 部门Controller
 * 
 * @author YeGuanYao
 * @date Tue Sep 06 16:19:00 CST 2022
 */
@RestController
@RequestMapping("/api/dept/dept")
public class DeptController
{
    @Autowired
    private IDeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping("/list")
    public R list(Dept dept, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Dept> list = deptService.selectDeptList(dept);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取部门详细信息
     */
    @GetMapping(value = "/{deptNo}")
    public R getInfo(@PathVariable("deptNo") Long deptNo)
    {
        return R.ok(20000,deptService.selectDeptById(deptNo));
    }

    /**
     * 新增部门
     */
    @PostMapping
    public R add(@RequestBody Dept dept)
    {
        int rows = deptService.insertDept(dept);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public R edit(@RequestBody Dept dept)
    {
         int rows = deptService.updateDept(dept);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除部门
     */
	@DeleteMapping("/{deptNos}")
    public R remove(@PathVariable Long[] deptNos)
    {
        int rows = deptService.deleteDeptByIds(deptNos);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}