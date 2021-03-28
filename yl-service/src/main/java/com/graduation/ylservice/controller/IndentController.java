package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.service.IndentService;
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
@RequestMapping("ylservice/indent")
public class IndentController {
    @Autowired
    private IndentService indentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:indent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = indentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{indentId}")
    //@RequiresPermissions("ylservice:indent:info")
    public R info(@PathVariable("indentId") String indentId){
		IndentEntity indent = indentService.getById(indentId);

        return R.ok().put("indent", indent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:indent:save")
    public R save(@RequestBody IndentEntity indent){
		indentService.save(indent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:indent:update")
    public R update(@RequestBody IndentEntity indent){
		indentService.updateById(indent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:indent:delete")
    public R delete(@RequestBody String[] indentIds){
		indentService.removeByIds(Arrays.asList(indentIds));

        return R.ok();
    }

}
