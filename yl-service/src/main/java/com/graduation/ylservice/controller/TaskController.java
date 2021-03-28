package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.TaskEntity;
import com.graduation.ylservice.service.TaskService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.R;



/**
 * 
 *
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@RestController
@RequestMapping("ylservice/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:task:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = taskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{pkTaskId}")
    //@RequiresPermissions("ylservice:task:info")
    public R info(@PathVariable("pkTaskId") String pkTaskId){
		TaskEntity task = taskService.getById(pkTaskId);

        return R.ok().put("task", task);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:task:save")
    public R save(@RequestBody TaskEntity task){
		taskService.save(task);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:task:update")
    public R update(@RequestBody TaskEntity task){
		taskService.updateById(task);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:task:delete")
    public R delete(@RequestBody String[] pkTaskIds){
		taskService.removeByIds(Arrays.asList(pkTaskIds));

        return R.ok();
    }

}
