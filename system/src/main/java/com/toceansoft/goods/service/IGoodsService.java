package com.toceansoft.goods.service;

import java.util.List;
import com.toceansoft.goods.entity.Goods;

/**
<<<<<<< HEAD
 * 商品管理Service接口
 * 
 * @author ygy
 * @date Wed Sep 07 14:22:42 CST 2022
=======
 * 商品Service接口
 * 
 * @author YeGuanYao
 * @date Wed Sep 07 09:30:41 CST 2022
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
 */
public interface IGoodsService 
{
    /**
<<<<<<< HEAD
     * 查询商品管理
     * 
     * @param id 商品管理ID
     * @return 商品管理
=======
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     */
    public Goods selectGoodsById(Long id);

    /**
<<<<<<< HEAD
     * 查询商品管理列表
     * 
     * @param goods 商品管理
     * @return 商品管理集合
=======
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
<<<<<<< HEAD
     * 新增商品管理
     * 
     * @param goods 商品管理
=======
     * 新增商品
     * 
     * @param goods 商品
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
<<<<<<< HEAD
     * 修改商品管理
     * 
     * @param goods 商品管理
=======
     * 修改商品
     * 
     * @param goods 商品
>>>>>>> 7e1c3c25025c072b8ff2ffbb79b920c5f07eec1a
     * @return 结果
     */
    public int updateGoods(Goods goods);

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
    public int deleteGoodsByIds(Long[] ids);

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
    public int deleteGoodsById(Long id);
}
