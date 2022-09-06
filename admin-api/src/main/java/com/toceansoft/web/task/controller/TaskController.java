package com.toceansoft.web.task.controller;


import com.toceansoft.common.util.R;
import com.toceansoft.task.entity.Task;
import com.toceansoft.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 任务列表管理器
 */
@RestController
//@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 根据用户id去查找所有属于该用户的任务
     * @param userId
     * @return
     */
//    @GetMapping("")
    public R list(Long userId){
        List<Task> taskList = taskService.selectList(userId);
//        System.out.println(userId);
//        System.out.println(taskList);
        return R.ok(20000,taskList);
    }

    /**
     * 批量删除任务
     * @param ids
     * @return
     */
//    @DeleteMapping("/{ids}")
    public R delete(Long[] ids){
        System.out.println("--------->"+ Arrays.toString(ids));
        int row = taskService.delete(ids);
        if(row > 0){
            return R.ok(20000,new String("删除成功！"));
        }
        else
        return R.fail(50010,"删除失败，该任务不存在！");
    }

    /**
     * 修改单个任务
     * @param task
     * @return
     */
//    @PutMapping("/update")
    public R update( Task task){
        int row = taskService.update(task);
        if(row > 0){
            return R.ok(20000,new String("修改成功！"));
        }
        else
            return R.fail(50011,"修改失败！");
    }

    /**
     * 添加个人任务
     * @param task
     * @return
     */
//    @PostMapping("")
    public R add( Task task){
        int rows = taskService.insert(task);
        if (rows <= 0 ) {
            return R.fail(50012, "添加失败!");
        }
        return R.ok(20000,"添加成功！");

    }

}
