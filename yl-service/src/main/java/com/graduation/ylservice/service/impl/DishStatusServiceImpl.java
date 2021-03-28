package com.graduation.ylservice.service.impl;

import com.graduation.ylservice.dao.DishStatusDao;
import com.graduation.ylservice.entity.DishStatusEntity;
import com.graduation.ylservice.service.DishStatusService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("dishStatusService")
public class DishStatusServiceImpl extends ServiceImpl<DishStatusDao, DishStatusEntity> implements DishStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DishStatusEntity> page = this.page(
                new Query<DishStatusEntity>().getPage(params),
                new QueryWrapper<DishStatusEntity>()
        );

        return new PageUtils(page);
    }

}