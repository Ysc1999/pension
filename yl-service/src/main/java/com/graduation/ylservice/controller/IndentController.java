package com.graduation.ylservice.controller;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.entity.AccountEntity;
import com.graduation.ylservice.entity.UserEntity;
import com.graduation.ylservice.entity.query.IndentQuery;
import com.graduation.ylservice.service.AccountService;
import com.graduation.ylservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    /**
     * 列表 返回指定支付状态的全部订单数据
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = indentService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据用户名、时间区间、支付状态 选择对应订单
     * @param indentQuery
     * @return
     */
    @PostMapping("/selectByParams")
    public R selectByParams(@RequestBody IndentQuery indentQuery){
        PageUtils page = indentService.selectByParams(indentQuery);
        return R.ok().put("page",page);
    }

    /**
     * 返回订单信息 + 下单用户信息 + 用户账户信息
     */
    @GetMapping("/info/{indentId}")
    //@RequiresPermissions("ylservice:indent:info")
    public R info(@PathVariable("indentId") String indentId){
		IndentEntity indent = indentService.getById(indentId);
        UserEntity user = userService.getById(indent.getInUserId());
        AccountEntity account = accountService.getOne(new QueryWrapper<AccountEntity>().eq("ac_user_id", user.getUserId()));
        return R.ok().put("indent", indent).put("user",user).put("account",account);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    //@RequiresPermissions("ylservice:indent:delete")
    public R delete(@RequestBody String[] indentIds){
        if(indentIds.length != 0){
            indentService.removeByIds(Arrays.asList(indentIds));
            return R.ok();
        }else{
            return R.error("请选择删除的菜品id");
        }
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("ylservice:indent:save")
    public R save(@RequestBody IndentEntity indent){
        indentService.saveIndent(indent);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("ylservice:indent:update")
    public R update(@RequestBody IndentEntity indent){
        indentService.updateById(indent);

        return R.ok();
    }
}

