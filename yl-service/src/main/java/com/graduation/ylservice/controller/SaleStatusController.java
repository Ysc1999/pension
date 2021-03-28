package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.SaleStatusEntity;
import com.graduation.ylservice.service.SaleStatusService;
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
@RequestMapping("/salestatus")
public class SaleStatusController {
    @Autowired
    private SaleStatusService saleStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions(":salestatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saleStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{saleDishId}")
    //@RequiresPermissions(":salestatus:info")
    public R info(@PathVariable("saleDishId") String saleDishId){
		SaleStatusEntity saleStatus = saleStatusService.getById(saleDishId);

        return R.ok().put("saleStatus", saleStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions(":salestatus:save")
    public R save(@RequestBody SaleStatusEntity saleStatus){
		saleStatusService.save(saleStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions(":salestatus:update")
    public R update(@RequestBody SaleStatusEntity saleStatus){
		saleStatusService.updateById(saleStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions(":salestatus:delete")
    public R delete(@RequestBody String[] saleDishIds){
		saleStatusService.removeByIds(Arrays.asList(saleDishIds));

        return R.ok();
    }

}
