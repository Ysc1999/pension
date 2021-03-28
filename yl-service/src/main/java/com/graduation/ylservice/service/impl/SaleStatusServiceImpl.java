package com.graduation.ylservice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.SaleStatusDao;
import com.graduation.ylservice.entity.SaleStatusEntity;
import com.graduation.ylservice.service.SaleStatusService;


@Service("saleStatusService")
public class SaleStatusServiceImpl extends ServiceImpl<SaleStatusDao, SaleStatusEntity> implements SaleStatusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SaleStatusEntity> page = this.page(
                new Query<SaleStatusEntity>().getPage(params),
                new QueryWrapper<SaleStatusEntity>()
        );

        return new PageUtils(page);
    }

}