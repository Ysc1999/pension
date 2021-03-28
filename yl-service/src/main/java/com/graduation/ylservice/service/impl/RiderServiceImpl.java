package com.graduation.ylservice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.RiderDao;
import com.graduation.ylservice.entity.RiderEntity;
import com.graduation.ylservice.service.RiderService;


@Service("riderService")
public class RiderServiceImpl extends ServiceImpl<RiderDao, RiderEntity> implements RiderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RiderEntity> page = this.page(
                new Query<RiderEntity>().getPage(params),
                new QueryWrapper<RiderEntity>()
        );

        return new PageUtils(page);
    }

}