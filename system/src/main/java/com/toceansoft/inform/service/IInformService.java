package com.toceansoft.inform.service;

import java.util.List;
import com.toceansoft.inform.entity.Inform;

/**
 * informService接口
 * 
 * @author chan_juhin
 * @date Wed Sep 07 10:39:38 CST 2022
 */
public interface IInformService 
{
    /**
     * 查询inform
     * 
     * @param id informID
     * @return inform
     */
    public Inform selectInformById(Long id);

    /**
     * 查询inform列表
     * 
     * @param inform inform
     * @return inform集合
     */
    public List<Inform> selectInformList(Inform inform);

    /**
     * 新增inform
     * 
     * @param inform inform
     * @return 结果
     */
    public int insertInform(Inform inform);

    /**
     * 修改inform
     * 
     * @param inform inform
     * @return 结果
     */
    public int updateInform(Inform inform);

    /**
     * 批量删除inform
     * 
     * @param ids 需要删除的informID
     * @return 结果
     */
    public int deleteInformByIds(Long[] ids);

    /**
     * 删除inform信息
     * 
     * @param id informID
     * @return 结果
     */
    public int deleteInformById(Long id);
}
