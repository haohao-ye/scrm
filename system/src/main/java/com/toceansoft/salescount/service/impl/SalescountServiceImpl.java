package com.toceansoft.salescount.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.salescount.mapper.SalescountMapper;
import com.toceansoft.salescount.entity.Salescount;
import com.toceansoft.salescount.service.ISalescountService;

/**
 * 销售统计Service业务层处理
 * 
 * @author hhh
 * @date Wed Sep 07 16:38:16 CST 2022
 */
@Service
public class SalescountServiceImpl implements ISalescountService 
{
    @Autowired
    private SalescountMapper salescountMapper;

    /**
     * 查询销售统计
     * 
     * @param countId 销售统计ID
     * @return 销售统计
     */
    @Override
    public Salescount selectSalescountById(Long countId)
    {
        return salescountMapper.selectSalescountById(countId);
    }

    /**
     * 查询销售统计列表
     * 
     * @param salescount 销售统计
     * @return 销售统计
     */
    @Override
    public List<Salescount> selectSalescountList(Salescount salescount)
    {
        return salescountMapper.selectSalescountList(salescount);
    }

    /**
     * 新增销售统计
     * 
     * @param salescount 销售统计
     * @return 结果
     */
    @Override
    public int insertSalescount(Salescount salescount)
    {
        salescount.setCreateTime(DateUtils.getNowDate());
        return salescountMapper.insertSalescount(salescount);
    }

    /**
     * 修改销售统计
     * 
     * @param salescount 销售统计
     * @return 结果
     */
    @Override
    public int updateSalescount(Salescount salescount)
    {
        return salescountMapper.updateSalescount(salescount);
    }

    /**
     * 批量删除销售统计
     * 
     * @param countIds 需要删除的销售统计ID
     * @return 结果
     */
    @Override
    public int deleteSalescountByIds(Long[] countIds)
    {
        return salescountMapper.deleteSalescountByIds(countIds);
    }

    /**
     * 删除销售统计信息
     * 
     * @param countId 销售统计ID
     * @return 结果
     */
    @Override
    public int deleteSalescountById(Long countId)
    {
        return salescountMapper.deleteSalescountById(countId);
    }
}
