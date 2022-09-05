package com.toceansoft.admin.mapper;


import com.toceansoft.admin.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理員mapper接口
 *
 */
@Mapper
public interface AdminMapper {
    /**
     * 增
     * @param admin
     * @return
     */
    public int insert(Admin admin);
    public int delete(Long id);
    public int delete(@Param("ids") Long[] ids);
    public int update(Admin admin);

    /**
     * 分頁查詢
     * @param admin
     * @return
     */
    public List<Admin> selectList(Admin admin);

    /**
     * 單個查
     * @param id
     * @return
     */
    public Admin selectById(Long id);
}
