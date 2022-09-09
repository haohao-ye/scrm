package com.toceansoft.goods.mapper;

import java.util.List;

import com.toceansoft.goods.entity.EntryOrder;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.goods.entity.Goods;

/**

 * 商品Mapper接口
 * 
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
 */
 @Mapper
public interface GoodsMapper 
{
    /**

     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    public Goods selectGoodsById(Long id);

    /**

     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**

     * 删除商品
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**

     * 批量删除商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsByIds(Long[] ids);

    /**
     * 订单入库
     * @param entryOrder
     * @return
     */
    public int updateByOrder(EntryOrder entryOrder);

}
