package com.toceansoft.web.employee.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;
import io.jsonwebtoken.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.employee.entity.Employee;
import com.toceansoft.employee.service.IEmployeeService;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工管理Controller
 * 
 * @author chan_juhin
 * @date Wed Sep 07 14:43:57 CST 2022
 */
@RestController
@RequestMapping("/api/employee/employee")
public class EmployeeController
{
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询员工管理列表
     */
    @GetMapping("/list")
    public R list(Employee employee, @RequestParam int pageNum, @RequestParam int pageSize)
    {
        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Employee> list = employeeService.selectEmployeeList(employee);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取员工管理详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.ok(20000,employeeService.selectEmployeeById(id));
    }

    /**
     * 新增员工管理
     */
    @PostMapping
    public R add(@RequestBody Employee employee, HttpServletRequest req)
    {

        //自动生成符合规范的员工工号：年月+员工个数，如 20200206
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        Date date = new Date(System.currentTimeMillis());
        Long head =Long.parseLong(dateFormat.format(date))*100;
        System.out.println(head);
        Long id = Long.valueOf(employeeService.counter(employee));
        employee.setId(head+id);

        //提取身份证里员工生日，自动生成初始密码
        employee.setPassword(employee.getIdNum().substring(6,14));

        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        employee.setUpdateBy(admin.getUsername());
        employee.setCreateBy(admin.getUsername());

        int rows = employeeService.insertEmployee(employee);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }

        return R.ok(20000, null);
    }

    /**
     * 修改员工管理
     */
    @PutMapping
    public R edit(@RequestBody Employee employee,HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        employee.setUpdateBy(admin.getUsername());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        employee.setUpdateTime(date);

//        System.out.println(employee);
        int rows = employeeService.updateEmployee(employee);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }

        return R.ok(20000, null);
    }

    /**
     * 删除员工管理
     */
	@DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids,HttpServletRequest req)
    {
        String token=req.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        for (Long id :ids
        ) {
            Employee employee = employeeService.selectEmployeeById(id);
            employee.setUpdateBy(admin.getUsername());
        }
        int rows = employeeService.deleteEmployeeByIds(ids);
         if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 重置员工登录密码
     * @return
     */
    @PutMapping("/resetPw")
    public R resetPw(@RequestBody Employee employee, HttpServletRequest req){
        //提取身份证里员工生日，重置为初始密码:yyyymmdd
        employee.setPassword(employee.getIdNum().substring(6,14));
        int rows = employeeService.updateEmployee(employee);
        if (rows <= 0 ) {
            return R.fail(50002, "重置密码失败！");
        }
        return R.ok(20000, null);
    }

    /**
     * 导入excel
     * @return
     */
    @PutMapping("/{path}")
    public R getImport(String fileName,HttpServletRequest req) throws IOException, java.io.IOException {
        File file = new File(fileName);
        if(!file.exists()){
            throw new IOException("文件不存在");
        }
        FileInputStream fin = new FileInputStream(String.valueOf(file));
        HSSFWorkbook workbook = new HSSFWorkbook(fin);
        //循环工作簿
        List<Employee> employees= new ArrayList<>();
        //取第一行的下一行
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum(); i++) {
            //获取行
            HSSFRow row = sheet.getRow(i);
            if (row == null)
                continue;
            Employee employee = new Employee();
            employee.setName(row.getCell(0).getStringCellValue());//名字
            employee.setPhoneNumber(row.getCell(1).getStringCellValue());//手机号码
            employee.setIdNum(row.getCell(2).getStringCellValue());//身份证
            employee.setDeptName(row.getCell(3).getStringCellValue());//部门名
            employees.add(employee);
            add(employee,req);
            int rows = employeeService.updateEmployee(employee);
            if (rows <= 0 ) {
                return R.fail(50002, "导入失败");
            }
        }

        return R.ok(20000, null);
    }
}
