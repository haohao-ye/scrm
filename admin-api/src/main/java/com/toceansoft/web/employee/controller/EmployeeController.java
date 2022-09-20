package com.toceansoft.web.employee.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarFile;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.common.util.JWTUtils;

import java.io.File;


import com.toceansoft.dept.entity.Dept;
import com.toceansoft.dept.service.IDeptService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.toceansoft.common.util.R;
import com.toceansoft.employee.entity.Employee;
import com.toceansoft.employee.service.IEmployeeService;
import com.toceansoft.web.dept.controller.DeptController;

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

    @Autowired
    private IDeptService deptService;



    @RequestMapping("/wxlogin")
    public R wxlogin(String openid){
        System.out.println("openid:"+openid);
        Employee employee = employeeService.selectEmployeeByOpenid(openid);
        if(employee == null){
            return R.fail(25000,"用户微信未绑定手机号码");
        }

        //验证成功，生成token
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",employee.getName());

        String token= JWTUtils.getJwtToken(claims);
        Map<String,Object> data=new HashMap<>();
        data.put("token",token);
        return  R.ok(20001,employee.getId());
    }

    @RequestMapping("/wxbind")
    public R wxbind(String openid, String id){
        Employee employee = employeeService.selectEmployeeById(Long.parseLong(id));
        System.out.println("openid"+openid);
        System.out.println("id"+id);
        if(employee == null){
            return R.fail(25001,"用户不存在");
        }
        if(employee.getOpenid() != null && !employee.getOpenid().equals("")){
            return R.fail(25002,"用户微信已绑定");
        }
        System.out.println("ok!!!");
        employee.setOpenid(openid);
        employeeService.updateEmployee(employee);
        return R.ok(20001,"绑定成功");

    }



    /*小程序调用后台登录接口
     * */
    @RequestMapping("/doLogin")
    public R doLogin( String username,  String password){
//        System.out.println(username);
//        System.out.println(password);
        int ok = 1;
        for(int i = 0; i<username.length(); i++){
            if(username.charAt(i) > '9' || username.charAt(i) < '0'){
                return R.fail(50001,"用户名错误");
            }
        }
        Long id = Long.parseLong(username);
        //验证用户名和密码
        Employee employee = employeeService.selectEmployeeById(id);
        if(!employee.getPassword().equals(password)){
            return R.fail(50001,"用户名或密码错误");
        }


        //验证成功，生成token
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",employee.getName());

        String token= JWTUtils.getJwtToken(claims);
        Map<String,Object> data=new HashMap<>();
        data.put("token",token);
        System.out.println("openid"+employee.getOpenid());
        if(employee.getOpenid()==null || employee.getOpenid().equals("")){
            return R.ok(20009,"询问是否绑定微信");
        }
        return  R.ok(20001,data);
    }

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
     * 获取加密后的员工信息（手机号、身份证加密）
     * @param id
     * @return
     */
    @GetMapping(value = "/getCodedInfo/{id}")
    public R getCodedInfo(@PathVariable("id") Long id)
    {

        Employee e = employeeService.selectEmployeeById(id);
        System.out.println(id);
        StringBuffer phone = new StringBuffer(e.getPhoneNumber());
        StringBuffer idShow = new StringBuffer(e.getIdNum());
        phone = phone.replace(3,7,"****");
        idShow = idShow.replace(3,15,"************");
        e.setPhoneNumber(phone.toString());
        e.setIdNum(idShow.toString());
        return R.ok(20000,e);
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
     * 管理员重置员工登录密码
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
     * 员工修改自己的密码
     * @param employee
     * @return
     */
    @PostMapping("/changePsd")
    public R changePassword(@RequestBody Employee employee){
        System.out.println(employee);
        String pwd = employee.getPassword();
        String newPwd = employee.getNewPassword();
        employee = employeeService.selectEmployeeById(employee.getId());
        System.out.println(employee);
//        System.out.println("用户: "+e.getName()+" 正在修改密码");
//        System.out.println(e.getPassword());
        if(! pwd.equals(employee.getPassword())){
            return R.fail(50010,"原密码错误！");
        }
        employee.setPassword(newPwd);
        int rows = employeeService.updateEmployee(employee);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 员工重置自己的密码
     * @param employee
     * @return
     */
    @PutMapping("/resetPassword")
    public R resetPassword(@RequestBody Employee employee){

        Employee e = employeeService.selectEmployeeById(employee.getId());

        if(!e.getIdNum().equals(employee.getIdNum()) || !e.getPhoneNumber().equals(employee.getPhoneNumber())){
            return R.fail(50011,"手机号或身份证号验证失败，请仔细检查！");
        }
        employee.setPassword(e.getIdNum().substring(6,14));
        int rows = employeeService.updateEmployee(employee);
        if (rows <= 0 ) {
            return R.fail(50012, "重置密码失败！");
        }
        return R.ok(20000, null);
    }

    /**
     * 导入excel
     * @return
     */
    @PutMapping("/getImport")
    public R getImport(String fileName,HttpServletRequest req) throws IOException, java.io.IOException {
        System.out.println(fileName);
        File file = new File(fileName);
        if(!file.exists()){
            throw new IOException("文件不存在");
        }
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));//如果是xls，使用HSSFWorkbook；如果是xlsx，使用XSSFWorkbook,但是目前的xlsx在poi插件中有一点BUG和当前JDK11版本有冲突，所以只用了xls格式的表格
        //循环工作簿
        List<Employee> employees= new ArrayList<>();
        //取第一行的下一行
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = sheet.getFirstRowNum() + 1; i<=sheet.getLastRowNum(); i++) {
            //获取行
            HSSFRow row = sheet.getRow(i);
            if (row == null)
                continue;
            Employee employee = new Employee();
            row.getCell(0).setCellType(CellType.STRING);//改单元格的类型为spring，防止这类报错Cannot get a STRING value from a NUMERIC cell
            row.getCell(1).setCellType(CellType.STRING);
            row.getCell(2).setCellType(CellType.STRING);
            row.getCell(3).setCellType(CellType.STRING);
            employee.setName(row.getCell(0).getStringCellValue());//名字
            employee.setPhoneNumber(row.getCell(1).getStringCellValue());//手机号码
            employee.setIdNum(row.getCell(2).getStringCellValue());//身份证
            employee.setDeptName(row.getCell(3).getStringCellValue());//部门名
            Dept dept = new Dept();
            dept.setDeptName(employee.getDeptName());
            List<Dept> deptList = deptService.selectDeptList(dept);
            if(!deptList.isEmpty()){
                employee.setDeptId( deptList.get(0).getId());
            }else {
                employee.setDeptId(1L);
            }

            System.out.println(employee);
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
