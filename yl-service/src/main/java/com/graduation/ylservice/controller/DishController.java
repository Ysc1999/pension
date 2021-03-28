package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.DishEntity;
import com.graduation.ylservice.service.DishService;
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
@RequestMapping("ylservice/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:dish:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dishService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dishId}")
    //@RequiresPermissions("ylservice:dish:info")
    public R info(@PathVariable("dishId") String dishId){
		DishEntity dish = dishService.getById(dishId);

        return R.ok().put("dish", dish);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:dish:save")
    public R save(@RequestBody DishEntity dish){
		dishService.save(dish);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:dish:update")
    public R update(@RequestBody DishEntity dish){
		dishService.updateById(dish);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:dish:delete")
    public R delete(@RequestBody String[] dishIds){
		dishService.removeByIds(Arrays.asList(dishIds));

        return R.ok();
    }

}
