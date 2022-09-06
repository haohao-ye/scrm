package com.toceansoft.admin.service;

import com.toceansoft.admin.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 管理员服务层接口
 */

public interface AdminService {

        /**
         * 增加
         * @param admin
         * @return
         */
        public int insert(Admin admin);

        /**
         * 根据id删除
         * @param id
         * @return
         */
        public int delete(Long id);

        /**
         * 批量删除
         * @param ids
         * @return
         */
        public int delete(Long[] ids);

        /**
         * 修改
         * @param admin
         * @return
         */
        public int update(Admin admin);


        /**
         * 分页查询
         * @param admin
         * @return
         */
        public List<Admin> selectList(Admin admin);

        /**
         * 根据ID查出单个
         * @param id
         * @return
         */
        public Admin selectById(Long id);
        public Admin selectByUsername(String username);


        /**
         * 修改密码
         * @param password,username
         * @return
         */
        public void changePassword(Long username,String newPassword);
}
