package com.toceansoft.goods.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.goods.entity.Goods;

/**
 * 商品管理Mapper接口
 * 
 * @author ygy
 * @date Wed Sep 07 14:22:42 CST 2022
 */
 @Mapper
public interface GoodsMapper 
{
    /**
     * 查询商品管理
     * 
     * @param id 商品管理ID
     * @return 商品管理
     */
    public Goods selectGoodsById(Long id);

    /**
     * 查询商品管理列表
     * 
     * @param goods 商品管理
     * @return 商品管理集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品管理
     * 
     * @param goods 商品管理
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品管理
     * 
     * @param goods 商品管理
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 删除商品管理
     * 
     * @param id 商品管理ID
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**
     * 批量删除商品管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsByIds(Long[] ids);
}
