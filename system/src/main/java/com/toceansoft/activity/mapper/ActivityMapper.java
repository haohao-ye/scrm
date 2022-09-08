package com.toceansoft.activity.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.toceansoft.activity.entity.Activity;

/**
 * 营销活动Mapper接口
 * 
 * @author ygy
 * @date Thu Sep 08 10:20:59 CST 2022
 */
 @Mapper
public interface ActivityMapper 
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
     * 删除营销活动
     * 
     * @param id 营销活动ID
     * @return 结果
     */
    public int deleteActivityById(Long id);

    /**
     * 批量删除营销活动
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActivityByIds(Long[] ids);
}
