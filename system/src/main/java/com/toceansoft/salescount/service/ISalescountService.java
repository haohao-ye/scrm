package com.toceansoft.salescount.service;

import java.util.List;
import com.toceansoft.salescount.entity.Salescount;

/**
 * 销售统计Service接口
 * 
 * @author hhh
 * @date Wed Sep 07 16:38:16 CST 2022
 */
public interface ISalescountService 
{
    /**
     * 查询销售统计
     * 
     * @param countId 销售统计ID
     * @return 销售统计
     */
    public Salescount selectSalescountById(Long countId);

    /**
     * 查询销售统计列表
     * 
     * @param salescount 销售统计
     * @return 销售统计集合
     */
    public List<Salescount> selectSalescountList(Salescount salescount);

    /**
     * 新增销售统计
     * 
     * @param salescount 销售统计
     * @return 结果
     */
    public int insertSalescount(Salescount salescount);

    /**
     * 修改销售统计
     * 
     * @param salescount 销售统计
     * @return 结果
     */
    public int updateSalescount(Salescount salescount);

    /**
     * 批量删除销售统计
     * 
     * @param countIds 需要删除的销售统计ID
     * @return 结果
     */
    public int deleteSalescountByIds(Long[] countIds);

    /**
     * 删除销售统计信息
     * 
     * @param countId 销售统计ID
     * @return 结果
     */
    public int deleteSalescountById(Long countId);
}
