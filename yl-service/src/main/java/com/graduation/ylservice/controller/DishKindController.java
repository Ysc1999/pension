package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.DishKindEntity;
import com.graduation.ylservice.service.DishKindService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.R;



/**
 * 
 *
 * @author Yourself
 * @email NOPE@gmail.com
 * @date 2021-03-27 22:58:44
 */
@RestController
@RequestMapping("/dishkind")
public class DishKindController {
    @Autowired
    private DishKindService dishKindService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions(":dishkind:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dishKindService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{kindId}")
    //@RequiresPermissions(":dishkind:info")
    public R info(@PathVariable("kindId") String kindId){
		DishKindEntity dishKind = dishKindService.getById(kindId);

        return R.ok().put("dishKind", dishKind);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions(":dishkind:save")
    public R save(@RequestBody DishKindEntity dishKind){
		dishKindService.save(dishKind);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions(":dishkind:update")
    public R update(@RequestBody DishKindEntity dishKind){
		dishKindService.updateById(dishKind);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions(":dishkind:delete")
    public R delete(@RequestBody String[] kindIds){
		dishKindService.removeByIds(Arrays.asList(kindIds));

        return R.ok();
    }

}
