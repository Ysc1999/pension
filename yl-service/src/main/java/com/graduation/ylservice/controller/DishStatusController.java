package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.DishStatusEntity;
import com.graduation.ylservice.service.DishStatusService;
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
@RequestMapping("ylservice/dishstatus")
public class DishStatusController {
    @Autowired
    private DishStatusService dishStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:dishstatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dishStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stDishId}")
    //@RequiresPermissions("ylservice:dishstatus:info")
    public R info(@PathVariable("stDishId") String stDishId){
		DishStatusEntity dishStatus = dishStatusService.getById(stDishId);

        return R.ok().put("dishStatus", dishStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:dishstatus:save")
    public R save(@RequestBody DishStatusEntity dishStatus){
		dishStatusService.save(dishStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:dishstatus:update")
    public R update(@RequestBody DishStatusEntity dishStatus){
		dishStatusService.updateById(dishStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:dishstatus:delete")
    public R delete(@RequestBody String[] stDishIds){
		dishStatusService.removeByIds(Arrays.asList(stDishIds));

        return R.ok();
    }

}
