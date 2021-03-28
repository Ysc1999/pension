package com.graduation.ylservice.service.impl;

import com.graduation.ylservice.utils.Query;
import com.graduation.ylservice.dao.DishDao;
import com.graduation.ylservice.entity.DishEntity;
import com.graduation.ylservice.service.DishService;
import com.graduation.ylservice.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("dishService")
public class DishServiceImpl extends ServiceImpl<DishDao, DishEntity> implements DishService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DishEntity> page = this.page(
                new Query<DishEntity>().getPage(params),
                new QueryWrapper<DishEntity>()
        );

        return new PageUtils(page);
    }

}