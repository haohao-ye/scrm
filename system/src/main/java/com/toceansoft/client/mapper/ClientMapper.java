package com.toceansoft.client.mapper;


import com.toceansoft.client.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author zengqf
 * @date Wed Sep 07 09:52:38 CST 2022
 */
@Mapper
public interface ClientMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Client selectClientById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param Client 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Client> selectClientList(Client Client);

    /**
     * 新增【请填写功能名称】
     *
     * @param Client 【请填写功能名称】
     * @return 结果
     */
    public int insertClient(Client Client);

    /**
     * 修改【请填写功能名称】
     *
     * @param Client 【请填写功能名称】
     * @return 结果
     */
    public int updateClient(Client Client);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClientById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClientByIds(Long[] ids);
}
