package com.toceansoft.salescount.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.salescount.mapper.SalescountMapper;
import com.toceansoft.salescount.entity.Salescount;
import com.toceansoft.salescount.service.ISalescountService;

/**
 * 销售统计Service业务层处理
 * 
 * @author zengqf
 * @date Thu Sep 08 15:08:48 CST 2022
 */
@Service
public class SalescountServiceImpl implements ISalescountService 
{
    @Autowired
    private SalescountMapper salescountMapper;

    /**
     * 查询销售统计
     * 
     * @param goodsId 销售统计ID
     * @return 销售统计
     */
    @Override
    public Salescount selectSalescountById(Long goodsId)
    {
        return salescountMapper.selectSalescountById(goodsId);
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
     * @param goodsIds 需要删除的销售统计ID
     * @return 结果
     */
    @Override
    public int deleteSalescountByIds(Long[] goodsIds)
    {
        return salescountMapper.deleteSalescountByIds(goodsIds);
    }

    /**
     * 删除销售统计信息
     * 
     * @param goodsId 销售统计ID
     * @return 结果
     */
    @Override
    public int deleteSalescountById(Long goodsId)
    {
        return salescountMapper.deleteSalescountById(goodsId);
    }
}
