package com.toceansoft.task.mapper;


import com.toceansoft.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    /**
     * 添加任务
     * @param task
     * @return
     */
    public int insert(Task task);

    /**
     * 删除任务
     * @param id
     * @return
     */
    public int delete(Long[] id);

    /**
     * 修改任务
     * @param task
     * @return
     */
    public int update(Task task);

    /**
     * 查找对应id的所有任务
     * @param userId
     * @return
     */
    public List<Task> selectList(Long userId);
}