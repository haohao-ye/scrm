package com.toceansoft.employee.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.employee.mapper.EmployeeMapper;
import com.toceansoft.employee.entity.Employee;
import com.toceansoft.employee.service.IEmployeeService;

/**
 * 员工管理Service业务层处理
 * 
 * @author chan_juhin
 * @date Wed Sep 07 14:43:57 CST 2022
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询员工管理
     * 
     * @param id 员工管理ID
     * @return 员工管理
     */
    @Override
    public Employee selectEmployeeById(Long id)
    {
        return employeeMapper.selectEmployeeById(id);
    }

    /**
     * 查询员工管理列表
     * 
     * @param employee 员工管理
     * @return 员工管理
     */
    @Override
    public List<Employee> selectEmployeeList(Employee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }

    /**
     * 新增员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    @Override
    public int insertEmployee(Employee employee)
    {
        return employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    @Override
    public int updateEmployee(Employee employee)
    {
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 批量删除员工管理
     * 
     * @param ids 需要删除的员工管理ID
     * @return 结果
     */
    @Override
    public int deleteEmployeeByIds(Long[] ids)
    {
        return employeeMapper.deleteEmployeeByIds(ids);
    }

    /**
     * 删除员工管理信息
     * 
     * @param id 员工管理ID
     * @return 结果
     */
    @Override
    public int deleteEmployeeById(Long id)
    {
        return employeeMapper.deleteEmployeeById(id);
    }

    /**
     * 统计员工个数
     * @param employee
     * @return
     */
    @Override
    public int counter(Employee employee) {
        return employeeMapper.counter(employee);
    }
}
