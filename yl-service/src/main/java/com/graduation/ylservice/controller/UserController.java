package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.graduation.ylservice.entity.UserEntity;
import com.graduation.ylservice.service.UserService;
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
@RequestMapping("ylservice/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("ylservice:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{userId}")
    //@RequiresPermissions("ylservice:user:info")
    public R info(@PathVariable("userId") String userId){
		UserEntity user = userService.getById(userId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("ylservice:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("ylservice:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("ylservice:user:delete")
    public R delete(@RequestBody String[] userIds){
		userService.removeByIds(Arrays.asList(userIds));

        return R.ok();
    }

}
