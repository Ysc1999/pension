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
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@RestController
@RequestMapping("ylservice/salestatus")
public class SaleStatusController {
    @Autowired
    private SaleStatusService saleStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:salestatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saleStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{saleStatusId}")
    //@RequiresPermissions("ylservice:salestatus:info")
    public R info(@PathVariable("saleStatusId") String saleStatusId){
		SaleStatusEntity saleStatus = saleStatusService.getById(saleStatusId);

        return R.ok().put("saleStatus", saleStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:salestatus:save")
    public R save(@RequestBody SaleStatusEntity saleStatus){
		saleStatusService.save(saleStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:salestatus:update")
    public R update(@RequestBody SaleStatusEntity saleStatus){
		saleStatusService.updateById(saleStatus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:salestatus:delete")
    public R delete(@RequestBody String[] saleStatusIds){
		saleStatusService.removeByIds(Arrays.asList(saleStatusIds));

        return R.ok();
    }

}
