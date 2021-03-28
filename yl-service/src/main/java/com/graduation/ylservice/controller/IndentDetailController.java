package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.IndentDetailEntity;
import com.graduation.ylservice.service.IndentDetailService;
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
@RequestMapping("ylservice/indentdetail")
public class IndentDetailController {
    @Autowired
    private IndentDetailService indentDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:indentdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = indentDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deIndentId}")
    //@RequiresPermissions("ylservice:indentdetail:info")
    public R info(@PathVariable("deIndentId") String deIndentId){
		IndentDetailEntity indentDetail = indentDetailService.getById(deIndentId);

        return R.ok().put("indentDetail", indentDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:indentdetail:save")
    public R save(@RequestBody IndentDetailEntity indentDetail){
		indentDetailService.save(indentDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:indentdetail:update")
    public R update(@RequestBody IndentDetailEntity indentDetail){
		indentDetailService.updateById(indentDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:indentdetail:delete")
    public R delete(@RequestBody String[] deIndentIds){
		indentDetailService.removeByIds(Arrays.asList(deIndentIds));

        return R.ok();
    }

}
