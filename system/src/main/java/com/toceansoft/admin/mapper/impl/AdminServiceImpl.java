package com.toceansoft.admin.mapper.impl;

import com.toceansoft.admin.entity.Admin;
import com.toceansoft.admin.mapper.AdminMapper;
import com.toceansoft.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.delete(id);
    }

    @Override
    public int delete(Long[] ids) {
        return adminMapper.delete(ids);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public List<Admin> selectList(Admin admin) {
        return adminMapper.selectList(admin);
    }

    @Override
    public Admin selectById(Long id) {
        return adminMapper.selectById(id);
    }

    @Override
    public Admin selectByUsername(String username) {
        Admin queryCond=new Admin();
        queryCond.setUsername((username));
        List<Admin> adminList=selectList((queryCond));
        if(adminList==null||adminList.size()<=0){
            return  null;
        }
        return adminList.get(0);
    }
}
