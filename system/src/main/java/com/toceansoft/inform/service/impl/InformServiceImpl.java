package com.toceansoft.inform.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.inform.mapper.InformMapper;
import com.toceansoft.inform.entity.Inform;
import com.toceansoft.inform.service.IInformService;

/**
 * informService业务层处理
 * 
 * @author chan_juhin
 * @date Wed Sep 07 10:39:38 CST 2022
 */
@Service
public class InformServiceImpl implements IInformService 
{
    @Autowired
    private InformMapper informMapper;

    /**
     * 查询inform
     * 
     * @param id informID
     * @return inform
     */
    @Override
    public Inform selectInformById(Long id)
    {
        return informMapper.selectInformById(id);
    }

    /**
     * 查询inform列表
     * 
     * @param inform inform
     * @return inform
     */
    @Override
    public List<Inform> selectInformList(Inform inform)
    {
        return informMapper.selectInformList(inform);
    }

    /**
     * 新增inform
     * 
     * @param inform inform
     * @return 结果
     */
    @Override
    public int insertInform(Inform inform)
    {
        inform.setCreateTime(DateUtils.getNowDate());
        return informMapper.insertInform(inform);
    }

    /**
     * 修改inform
     * 
     * @param inform inform
     * @return 结果
     */
    @Override
    public int updateInform(Inform inform)
    {
        inform.setUpdateTime(DateUtils.getNowDate());
        return informMapper.updateInform(inform);
    }

    /**
     * 批量删除inform
     * 
     * @param ids 需要删除的informID
     * @return 结果
     */
    @Override
    public int deleteInformByIds(Long[] ids)
    {
        return informMapper.deleteInformByIds(ids);
    }

    /**
     * 删除inform信息
     * 
     * @param id informID
     * @return 结果
     */
    @Override
    public int deleteInformById(Long id)
    {
        return informMapper.deleteInformById(id);
    }
}
