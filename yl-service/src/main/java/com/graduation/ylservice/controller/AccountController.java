package com.graduation.ylservice.controller;

import java.util.Arrays;
import java.util.Map;

import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.ylservice.entity.AccountEntity;
import com.graduation.ylservice.service.AccountService;



/**
 * 
 *
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@RestController
@RequestMapping("ylservice/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ylservice:account:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{accountId}")
    //@RequiresPermissions("ylservice:account:info")
    public R info(@PathVariable("accountId") String accountId){
		AccountEntity account = accountService.getById(accountId);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ylservice:account:save")
    public R save(@RequestBody AccountEntity account){
		accountService.save(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ylservice:account:update")
    public R update(@RequestBody AccountEntity account){
		accountService.updateById(account);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ylservice:account:delete")
    public R delete(@RequestBody String[] accountIds){
		accountService.removeByIds(Arrays.asList(accountIds));

        return R.ok();
    }

}
