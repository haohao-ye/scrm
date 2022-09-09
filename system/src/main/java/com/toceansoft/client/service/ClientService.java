package com.toceansoft.client.service;


import com.toceansoft.client.entity.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author zengqf
 * @date Wed Sep 07 09:52:38 CST 2022
 */
public interface ClientService
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
     * @param client 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Client> selectClientList(@Param("client") Client client);

    /**
     * 新增【请填写功能名称】
     *
     * @param client 【请填写功能名称】
     * @return 结果
     */
    public int insertClient(Client client);

    /**
     * 修改【请填写功能名称】
     *
     * @param client 【请填写功能名称】
     * @return 结果
     */
    public int updateClient(Client client);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClientByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteClientById(Long id);
}
