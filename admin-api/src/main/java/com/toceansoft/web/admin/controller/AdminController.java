package com.toceansoft.web.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.admin.service.AdminService;
import com.toceansoft.common.entity.PasswordInfo;
import com.toceansoft.common.util.JWTUtils;
import com.toceansoft.common.util.R;
import com.toceansoft.task.entity.Task;
import com.toceansoft.web.task.controller.TaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理Controller
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;//加密

    @Autowired
    private TaskController taskController;

    /**
     * 登录接口
     * @param admin
     * @return
     */
    @RequestMapping("/login")
    public R login(@RequestBody Admin admin){
        Admin tmp = adminService.selectByUsername(admin.getUsername());
        Admin tmp1 = adminService.selectEByUsername(admin.getUsername());

        if (null != tmp && admin.getPassword().equals(tmp.getPassword())) {  // 说明该用户是管理员
            tmp.setRole("admin");
            redisTemplate.opsForValue().set("LoginInfo_" + admin.getUsername(), tmp);
        } else if (null != tmp1 && admin.getPassword().equals(tmp1.getPassword())) {  // 说明该用户是员工
            if(tmp1.getDeptId() == 1)  // 销售部
                tmp1.setRole("sales");
            else  // 资产部
                tmp1.setRole("assets");
            redisTemplate.opsForValue().set("LoginInfo_" + admin.getUsername(), tmp1);
        } else {
            return R.fail(50001,"用户名或密码错误");
        }

        //验证成功，生成token
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",admin.getUsername());

        String token= JWTUtils.getJwtToken(claims);
        Map<String,Object> data=new HashMap<>();
        data.put("token",token);
        return  R.ok(20000,data);
    }

    /**
     *修改密码
     * @param passwordInfo,req
     * @return
     */
    @RequestMapping("/changepw")

    public R ChangePassword(PasswordInfo passwordInfo,HttpServletRequest req){
        //获得request请求
        //获得token
        String token=req.getHeader("X-Token");
        String username =JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        String oldPassword=admin.getPassword();
        //验证用户原密码
        if(null==oldPassword|| encoder.matches(oldPassword,passwordInfo.getNewPassword())){
            return R.fail(50001,"原密码错误");
        }
        if(!passwordInfo.getNewPassword().equals(passwordInfo.getVeriedPassword())){
            return R.fail(50001,"密码确认失败");
        }
        String newPassword=encoder.encode(admin.getNewPassword());
        adminService.changePassword(admin.getId(),newPassword);
        return R.ok(20000,"修改成功");
    }

    /**
     * 管理员信息接口
     * @param req
     * @return
     */
    @RequestMapping("/info")
    public R info(HttpServletRequest req){
        //获得request请求
        //获得token
        String token=req.getHeader("X-Token");
        String username =JWTUtils.getUsername(token);
        //从Regis缓存中读取当前登录的用户
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
//        System.out.println(admin.getUsername()+"       "+admin.getPassword());

        //读取登录用户信息
        Map<String,Object> data=new HashMap<>();
        data.put("name",admin.getNickname())   ;
        data.put("avatar",admin.getAvatar()) ;
        data.put("introdution",admin.getRemark()) ;

        String[] roles = {admin.getRole()};  // 权限管理判断角色时，采用数组格式的数据
        data.put("roles", roles);
        return  R.ok(20000,data);
    }

    /**
     * 登出接口
     * @param admin
     * @return
     */
    @RequestMapping("/logout")
    public R logout(Admin admin){
        String data ="success";
        return  R.ok(20000,data);
    }

    /**
     * 分页查询
     * @param admin
     * @return
     */
    @GetMapping("")
    public R list(Admin admin,@RequestParam int pageNo,@RequestParam int pageSize){
        PageHelper.startPage(pageNo,pageSize);//指定分页
        List<Admin> adminList=adminService.selectList(admin);
        PageInfo pageInfo=new PageInfo(adminList);//构建分页对象
        return R.ok(20000,pageInfo);
    }

    /**
     * 获取用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public  R get(@PathVariable Long id){
        Admin admin =adminService.selectById(id);
        return R.ok(20000,admin);
    }

    /**
     * 批量删除管理员用户
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable Long[] ids){
        System.out.printf("ids");
        int t=adminService.delete(ids);
        if(t>0){
            return R.ok(20000,"删除成功");

        }
        return  R.fail(0,"删除失败");
    }

    /**
     * 添加管理员
     * @param admin
     * @param req
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Admin admin,HttpServletRequest req){
        String token=req.getHeader("X-Token");
        String username =JWTUtils.getUsername(token);
        Admin queryCond=new Admin();
        queryCond.setUsername(admin.getUsername());
        List<Admin> adminList=adminService.selectList(queryCond);
        if(null!=adminList&&adminList.size()>0){
            return R.fail(50003,"用户已存在");
        }
        //密码加密
        String encodedPw=encoder.encode(admin.getPassword());
//        System.out.println(encodedPw);
        admin.setPassword(encodedPw);//将密码设为加密后的密码


        int rows=adminService.insert(admin);
        if(rows<=0){
            return R.fail(50001,"添加成功");

        }
        return R.ok(20000,"添加失败");
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @PutMapping("/edit")
    public R edit(@RequestBody Admin admin){
        System.out.println(admin.getUsername());
        //不允许修改
//        admin.setUsername(null);
        admin.setPassword(null);


        int rows=adminService.update(admin);
        if(rows<=0){
            return R.fail(0,"修改成功");

        }
        return R.ok(20000,"修改失败");
    }

    /**
     * 重置管理员密码
     * @param admin
     * @return
     */
    @PutMapping("/resetPw")
    public R resetPw(@RequestBody Admin admin){
        //管理员密码重置为123456
//        System.out.println("重置密码");
//        System.out.println(admin);
        admin.setNewPassword("123456");
        adminService.changePassword(admin.getId(),admin.getNewPassword());
        return R.ok(20000,"重置成功");
    }


    /**
     * 获取用户的任务列表
     * @param userId
     * @return
     */
    @GetMapping("/task")
    public R listTask(Long userId){
        return  taskController.list(userId);
    }

    /**
     * 批量删除用户的任务
     * @param ids
     * @return
     */
    @DeleteMapping("/task/{ids}")
    public R deleteTask(Long[] ids){
        return taskController.delete(ids);
    }

    /**
     * 修改用的单个任务
     * @param task
     * @return
     */
    @PutMapping("/task/update")
    public R updateTask(Task task){
        return taskController.update(task);
    }

    /**
     * 添加个人任务
     * @param task
     * @return
     */
    @PostMapping("/task")
    public R addTask(Task task){
        return taskController.add(task);
    }


}
