package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.RiderEntity;
import com.graduation.ylservice.service.RiderService;
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
@RequestMapping("/rider")
public class RiderController {
    @Autowired
    private RiderService riderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions(":rider:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = riderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{pkRiderId}")
    //@RequiresPermissions(":rider:info")
    public R info(@PathVariable("pkRiderId") String pkRiderId){
		RiderEntity rider = riderService.getById(pkRiderId);

        return R.ok().put("rider", rider);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions(":rider:save")
    public R save(@RequestBody RiderEntity rider){
		riderService.save(rider);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions(":rider:update")
    public R update(@RequestBody RiderEntity rider){
		riderService.updateById(rider);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions(":rider:delete")
    public R delete(@RequestBody String[] pkRiderIds){
		riderService.removeByIds(Arrays.asList(pkRiderIds));

        return R.ok();
    }

}
