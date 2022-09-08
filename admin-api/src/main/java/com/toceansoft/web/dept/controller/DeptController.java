package com.toceansoft.web.dept.controller;

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
import com.toceansoft.dept.entity.Dept;
import com.toceansoft.dept.service.IDeptService;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private RedisTemplate redisTemplate;

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
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,deptService.selectDeptById(id));
    }

    /**
     * 新增部门
     */
    @PostMapping
    public R add(@RequestBody Dept dept, HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        dept.setCreateBy(admin.getUsername());
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        Dept dept1=new Dept();
        dept1.setDeptName(dept.getDeptName());

        if(null!=dept){
            for (Dept d:deptService.selectDeptList(dept1)
                 ) {
                if (d.getDeptName().equals(dept.getDeptName())
                ) {
                    return R.fail(0,"已存在");
                }
            }
        }
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
    public R edit(@RequestBody Dept dept,HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        dept.setUpdateBy(admin.getUsername());
        dept.setUpdateTime(new Date());
         int rows = deptService.updateDept(dept);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除部门
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        int rows = deptService.deleteDeptByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }
}
