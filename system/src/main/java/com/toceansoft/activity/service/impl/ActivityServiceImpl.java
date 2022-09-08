package com.toceansoft.activity.service.impl;

import java.util.List;
import com.toceansoft.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toceansoft.activity.mapper.ActivityMapper;
import com.toceansoft.activity.entity.Activity;
import com.toceansoft.activity.service.IActivityService;

/**
 * 营销活动Service业务层处理
 * 
 * @author ygy
 * @date Thu Sep 08 10:20:59 CST 2022
 */
@Service
public class ActivityServiceImpl implements IActivityService 
{
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询营销活动
     * 
     * @param id 营销活动ID
     * @return 营销活动
     */
    @Override
    public Activity selectActivityById(Long id)
    {
        return activityMapper.selectActivityById(id);
    }

    /**
     * 查询营销活动列表
     * 
     * @param activity 营销活动
     * @return 营销活动
     */
    @Override
    public List<Activity> selectActivityList(Activity activity)
    {
        return activityMapper.selectActivityList(activity);
    }

    /**
     * 新增营销活动
     * 
     * @param activity 营销活动
     * @return 结果
     */
    @Override
    public int insertActivity(Activity activity)
    {
        return activityMapper.insertActivity(activity);
    }

    /**
     * 修改营销活动
     * 
     * @param activity 营销活动
     * @return 结果
     */
    @Override
    public int updateActivity(Activity activity)
    {
        activity.setUpdateTime(DateUtils.getNowDate());
        return activityMapper.updateActivity(activity);
    }

    /**
     * 批量删除营销活动
     * 
     * @param ids 需要删除的营销活动ID
     * @return 结果
     */
    @Override
    public int deleteActivityByIds(Long[] ids)
    {
        return activityMapper.deleteActivityByIds(ids);
    }

    /**
     * 删除营销活动信息
     * 
     * @param id 营销活动ID
     * @return 结果
     */
    @Override
    public int deleteActivityById(Long id)
    {
        return activityMapper.deleteActivityById(id);
    }
}
