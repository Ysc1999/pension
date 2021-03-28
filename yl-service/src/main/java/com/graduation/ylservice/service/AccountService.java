package com.graduation.ylservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.ylservice.entity.AccountEntity;
import com.graduation.ylservice.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

