package com.graduation.ylservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.entity.DishKindEntity;

import java.util.Map;

/**
 * 
 *
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
public interface DishKindService extends IService<DishKindEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

