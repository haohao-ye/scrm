package com.toceansoft.dept.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.dept.mapper.DeptMapper;
import com.toceansoft.dept.entity.Dept;
import com.toceansoft.dept.service.IDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author YeGuanYao
 * @date Tue Sep 06 16:19:00 CST 2022
 */
@Service
public class DeptServiceImpl implements IDeptService 
{
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门
     * 
     * @param deptNo 部门ID
     * @return 部门
     */
    @Override
    public Dept selectDeptById(Long deptNo)
    {
        return deptMapper.selectDeptById(deptNo);
    }

    /**
     * 查询部门列表
     * 
     * @param dept 部门
     * @return 部门
     */
    @Override
    public List<Dept> selectDeptList(Dept dept)
    {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 新增部门
     * 
     * @param dept 部门
     * @return 结果
     */
    @Override
    public int insertDept(Dept dept)
    {
        dept.setCreateTime(DateUtils.getNowDate());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改部门
     * 
     * @param dept 部门
     * @return 结果
     */
    @Override
    public int updateDept(Dept dept)
    {
        dept.setUpdateTime(DateUtils.getNowDate());
        return deptMapper.updateDept(dept);
    }

    /**
     * 批量删除部门
     * 
     * @param deptNos 需要删除的部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptByIds(Long[] deptNos)
    {
        return deptMapper.deleteDeptByIds(deptNos);
    }

    /**
     * 删除部门信息
     * 
     * @param deptNo 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptNo)
    {
        return deptMapper.deleteDeptById(deptNo);
    }
}
