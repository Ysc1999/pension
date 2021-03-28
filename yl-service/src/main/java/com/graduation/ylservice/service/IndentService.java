package com.graduation.ylservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author Yourself
 * @email NOPE@gmail.com
 * @date 2021-03-27 22:58:44
 */
public interface IndentService extends IService<IndentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

