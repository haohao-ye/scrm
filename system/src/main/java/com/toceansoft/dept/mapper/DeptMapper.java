package com.toceansoft.dept.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.dept.entity.Dept;

/**
 * 部门Mapper接口
 * 
 * @author YeGuanYao
 * @date Tue Sep 06 16:19:00 CST 2022
 */
 @Mapper
public interface DeptMapper 
{
    /**
     * 查询部门
     * 
     * @param id 部门ID
     * @return 部门
     */
    public Dept selectDeptById(Long id);

    /**
     * 查询部门列表
     * 
     * @param dept 部门
     * @return 部门集合
     */
    public List<Dept> selectDeptList(Dept dept);

    /**
     * 新增部门
     * 
     * @param dept 部门
     * @return 结果
     */
    public int insertDept(Dept dept);

    /**
     * 修改部门
     * 
     * @param dept 部门
     * @return 结果
     */
    public int updateDept(Dept dept);

    /**
     * 删除部门
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long id);

    /**
     * 批量删除部门
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDeptByIds(Long[] ids);
}
