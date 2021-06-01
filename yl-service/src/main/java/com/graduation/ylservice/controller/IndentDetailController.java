package com.graduation.ylservice.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.entity.DishEntity;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.entity.query.IndentDetailResult;
import com.graduation.ylservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.ylservice.entity.IndentDetailEntity;
import com.graduation.ylservice.service.IndentDetailService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.R;


/**
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@RestController
@RequestMapping("ylservice/indentdetail")
public class IndentDetailController {
    @Autowired
    private IndentDetailService indentDetailService;

    @Autowired
    private DishService dishService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("ylservice:indentdetail:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = indentDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据订单号找订单详情信息并返回
     */
    @GetMapping("/info/{deIndentId}")
    //@RequiresPermissions("ylservice:indentdetail:info")
    public R info(@PathVariable("deIndentId") String deIndentId) {
        List<IndentDetailResult> list = indentDetailService.selectDetailById(deIndentId);
        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("ylservice:indentdetail:save")
    public R save(@RequestBody IndentDetailEntity indentDetail) {
        indentDetailService.save(indentDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("ylservice:indentdetail:update")
    public R update(@RequestBody IndentDetailEntity indentDetail) {
        indentDetailService.updateById(indentDetail);

        return R.ok();
    }

    /**
     * 删除指定订单子表内容
     */
    @DeleteMapping("/deleteIndentDetailDish/{indentId}/{dishId}")
    //@RequiresPermissions("ylservice:indentdetail:delete")
    public R deleteIndentDetailDish(@PathVariable("indentId") String indentId, @PathVariable("dishId") String dishId) {
        return indentDetailService.removeByDishId(indentId, dishId) ? R.ok() : R.error();
    }


}
