package com.toceansoft.goods.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import com.toceansoft.goods.entity.EntryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.goods.mapper.GoodsMapper;
import com.toceansoft.goods.entity.Goods;
import com.toceansoft.goods.service.IGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
 */
@Service
public class GoodsServiceImpl implements IGoodsService 
{
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    @Override
    public Goods selectGoodsById(Long id)
    {
        return goodsMapper.selectGoodsById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品
     */
    @Override
    public List<Goods> selectGoodsList(Goods goods)
    {
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int insertGoods(Goods goods)
    {
        return goodsMapper.insertGoods(goods);
    }

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    @Override
    public int updateGoods(Goods goods)
    {
        goods.setUpdateTime(DateUtils.getNowDate());
        return goodsMapper.updateGoods(goods);
    }

    /**
<<<<<<< HEAD
     * 批量删除商品管理
     * 
     * @param ids 需要删除的商品管理ID
=======
     * 批量删除商品
     * 
     * @param ids 需要删除的商品ID
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     * @return 结果
     */
    @Override
    public int deleteGoodsByIds(Long[] ids)
    {
        return goodsMapper.deleteGoodsByIds(ids);
    }

    /**
<<<<<<< HEAD
     * 删除商品管理信息
     * 
     * @param id 商品管理ID
=======
     * 删除商品信息
     * 
     * @param id 商品ID
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     * @return 结果
     */
    @Override
    public int deleteGoodsById(Long id)
    {
        return goodsMapper.deleteGoodsById(id);
    }

    @Override
    public  int updateByOrder(EntryOrder order){
        return goodsMapper.updateByOrder(order);
    }
}
