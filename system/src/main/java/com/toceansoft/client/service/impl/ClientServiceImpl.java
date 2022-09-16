package com.toceansoft.client.service.impl;

import com.toceansoft.client.entity.Client;
import com.toceansoft.client.mapper.ClientMapper;
import com.toceansoft.client.service.ClientService;
import com.toceansoft.common.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author zengqf
 * @date Wed Sep 07 09:52:38 CST 2022
 */
@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private ClientMapper clientMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Client selectClientById(Long id)
    {
        return clientMapper.selectClientById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param Client 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Client> selectClientList(Client Client)
    {
        return clientMapper.selectClientList(Client);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param Client 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertClient(Client Client)
    {
        return clientMapper.insertClient(Client);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param Client 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateClient(Client Client)
    {
        Client.setUpdateTime(DateUtils.getNowDate());
        return clientMapper.updateClient(Client);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteClientByIds(Long[] ids)
    {
        return clientMapper.deleteClientByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteClientById(Long id)
    {
        return clientMapper.deleteClientById(id);
    }

    @Override
    public List<Client> selectClientByName(String clientName) {
        return clientMapper.selectClientByName(clientName);
    }
}
