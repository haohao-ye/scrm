package com.toceansoft.employee.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.employee.entity.Employee;

/**
 * 员工管理Mapper接口
 * 
 * @author chan_juhin
 * @date Wed Sep 07 14:43:57 CST 2022
 */
 @Mapper
public interface EmployeeMapper 
{
    /**
     * 查询员工管理
     * 
     * @param id 员工管理ID
     * @return 员工管理
     */
    public Employee selectEmployeeById(Long id);

    /**
     * 查询员工管理列表
     * 
     * @param employee 员工管理
     * @return 员工管理集合
     */
    public List<Employee> selectEmployeeList(Employee employee);

    /**
     * 新增员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 删除员工管理
     * 
     * @param id 员工管理ID
     * @return 结果
     */
    public int deleteEmployeeById(Long id);

    /**
     * 批量删除员工管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEmployeeByIds(Long[] ids);

    /**
     * 统计员工个数
     * @param employee
     * @return
     */
    public int counter(Employee employee);
}
