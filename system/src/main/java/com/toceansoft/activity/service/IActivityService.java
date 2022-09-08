package com.toceansoft.activity.service;

import java.util.List;
import com.toceansoft.activity.entity.Activity;

/**
 * 营销活动Service接口
 * 
 * @author ygy
 * @date Thu Sep 08 10:20:59 CST 2022
 */
public interface IActivityService 
{
    /**
     * 查询营销活动
     * 
     * @param id 营销活动ID
     * @return 营销活动
     */
    public Activity selectActivityById(Long id);

    /**
     * 查询营销活动列表
     * 
     * @param activity 营销活动
     * @return 营销活动集合
     */
    public List<Activity> selectActivityList(Activity activity);

    /**
     * 新增营销活动
     * 
     * @param activity 营销活动
     * @return 结果
     */
    public int insertActivity(Activity activity);

    /**
     * 修改营销活动
     * 
     * @param activity 营销活动
     * @return 结果
     */
    public int updateActivity(Activity activity);

    /**
     * 批量删除营销活动
     * 
     * @param ids 需要删除的营销活动ID
     * @return 结果
     */
    public int deleteActivityByIds(Long[] ids);

    /**
     * 删除营销活动信息
     * 
     * @param id 营销活动ID
     * @return 结果
     */
    public int deleteActivityById(Long id);
}
