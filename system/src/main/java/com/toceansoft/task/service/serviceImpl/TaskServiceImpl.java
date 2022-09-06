package com.toceansoft.task.service.serviceImpl;


import com.toceansoft.task.entity.Task;
import com.toceansoft.task.mapper.TaskMapper;
import com.toceansoft.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int insert(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public int delete(Long[] id) {
        return taskMapper.delete(id);
    }

    @Override
    public int update(Task task) {
        return taskMapper.update(task);
    }

    @Override
    public List<Task> selectList(Long userId) {
        return taskMapper.selectList(userId);
    }
}
