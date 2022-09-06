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
        Admin tmp=adminService.selectByUsername(admin.getUsername());


        //验证用户名和密码
        if(null==tmp|| !encoder.matches(admin.getPassword(),tmp.getPassword())){
            return R.fail(50001,"用户名或密码错误");
        }

        //将登陆管理员的信息存放在Redis缓存
        redisTemplate.opsForValue().set("LoginInfo_"+admin.getUsername(),tmp);


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
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        System.out.println(admin.getUsername()+"       "+admin.getPassword());

        Map<String,Object> data=new HashMap<>();
        data.put("introdution","I am a super administrator") ;
        data.put("name","Admin")   ;
        data.put("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202009%2F23%2F20200923185609_rQUdj.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1664419642&t=bcfd3d02ead349f51737ebbb1ee30739") ;
        data.put("roles",new String[]{"admin"});
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

    @GetMapping("/{id}")
    public  R get(@PathVariable Long id){
        Admin admin =adminService.selectById(id);
        return R.ok(20000,admin);
    }

    @DeleteMapping("/{ids}")
    public R delete(@PathVariable Long[] ids){
        System.out.printf("ids");
        int t=adminService.delete(ids);
        if(t>0){
            return R.ok(20000,"删除成功");

        }
        return  R.fail(0,"删除失败");
    }

    @PostMapping("/add")
    public R add(@RequestBody Admin admin,HttpServletRequest req){
        String token=req.getHeader("X-Token");
        String username =JWTUtils.getUsername(token);
        Admin queryCond=new Admin();
        queryCond.setUsername(admin.getUsername());
        List<Admin> adminList=adminService.selectList(queryCond);
        if(null!=adminList&&adminList.size()>0){
            return R.fail(0,"已存在");
        }
        //密码加密
        String encodedPw=encoder.encode(admin.getPassword());
//        System.out.println(encodedPw);
        admin.setPassword(encodedPw);//将密码设为加密后的密码


        int rows=adminService.insert(admin);
        if(rows<=0){
            return R.fail(0,"shibai");

        }
        return R.ok(20000,"chenggong");
    }

    @PutMapping("/edit")
    public R edit(@RequestBody Admin admin){
        System.out.println(admin.getUsername());
        //不允许修改
//        admin.setUsername(null);
        admin.setPassword(null);


        int rows=adminService.update(admin);
        if(rows<=0){
            return R.fail(0,"shibai");

        }
        return R.ok(20000,"chenggong");
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
