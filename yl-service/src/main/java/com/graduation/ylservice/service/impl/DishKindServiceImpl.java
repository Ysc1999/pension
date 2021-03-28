package com.graduation.ylservice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.DishKindDao;
import com.graduation.ylservice.entity.DishKindEntity;
import com.graduation.ylservice.service.DishKindService;


@Service("dishKindService")
public class DishKindServiceImpl extends ServiceImpl<DishKindDao, DishKindEntity> implements DishKindService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DishKindEntity> page = this.page(
                new Query<DishKindEntity>().getPage(params),
                new QueryWrapper<DishKindEntity>()
        );

        return new PageUtils(page);
    }

}