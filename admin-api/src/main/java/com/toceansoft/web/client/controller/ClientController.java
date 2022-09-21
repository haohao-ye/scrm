package com.toceansoft.web.client.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.toceansoft.admin.entity.Admin;
import com.toceansoft.client.entity.Client;
import com.toceansoft.client.service.ClientService;
import com.toceansoft.common.util.JWTUtils;
import com.toceansoft.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 * @date Wed Sep 07 09:52:38 CST 2022
 */
@RestController
@CrossOrigin
@RequestMapping("/api/client")
public class ClientController
{
    @Autowired
    private ClientService clientService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询客户列表
     */
    @GetMapping("/list")
    //
    public R list(Client client, @RequestParam int pageNum, @RequestParam int pageSize)
    {

        PageHelper.startPage(pageNum, pageSize); //指定分页
        List<Client> list = clientService.selectClientList(client);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }

    @GetMapping("/lists")
    //
    public R lists(Client client)
    {
        List<Client> list = clientService.selectClientList(client);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, pageInfo);//返回分页对象
    }


    /**
     * 获取指定客户详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        Client client = clientService.selectClientById(id);

        System.out.println("---------------------------------"+client);
        return R.ok(20000,clientService.selectClientById(id));
    }

    /**
     * 新增客户
     */
    @PostMapping()
    public R add(@RequestBody Client client, HttpServletRequest request)
    {
        String token=request.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        client.setUpdatePerson(admin.getUsername());
        client.setCreator(admin.getUsername());
        client.setCreateTime(new Date());
        client.setUpdateTime(new Date());

        int rows = clientService.insertClient(client);
        if (rows <= 0 ) {
            return R.fail(50002, "添加失败");
        }
        return R.ok(20000, null);
    }


    /**
     * 修改客户
     */

    @PutMapping("")
    public R edit(@RequestBody Client client, HttpServletRequest request)
    {
        String token=request.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        client.setUpdatePerson(admin.getUsername());
        client.setUpdateTime(new Date());

        int rows = clientService.updateClient(client);
        if (rows <= 0 ) {
            return R.fail(50002, "修改失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 删除【请填写功能名称】
     */
    @DeleteMapping("/{ids}")
    //@PathVariable(name = "ids")
    public R remove(@PathVariable Long[] ids, HttpServletRequest request)
    {
        String token=request.getHeader("X-Token");
        String username = JWTUtils.getUsername(token);
        Admin admin=(Admin) redisTemplate.opsForValue().get("LoginInfo_"+username);
        for (Long id :ids) {
            Client client = clientService.selectClientById(id);
            client.setUpdatePerson(admin.getUsername());
        }

        System.out.println("======================================="+ids);
        int rows = clientService.deleteClientByIds(ids);
        if (rows <= 0 ) {
            return R.fail(50002, "删除失败");
        }
        return R.ok(20000, null);
    }

    /**
     * 根据客户姓名查询
     * @param clientName
     * @return
     */
    @GetMapping(value = "/{clientName}")
    public R getByName(@PathVariable("clientName") String clientName){
        List<Client> list = clientService.selectClientByName(clientName);
        PageInfo pageInfo = new PageInfo(list); //构建分页对象
        return R.ok(20000, null);
    }
}

