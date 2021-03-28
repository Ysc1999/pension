package com.graduation.ylservice.service.impl;

import com.graduation.ylservice.dao.IndentDetailDao;
import com.graduation.ylservice.entity.IndentDetailEntity;
import com.graduation.ylservice.service.IndentDetailService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("indentDetailService")
public class IndentDetailServiceImpl extends ServiceImpl<IndentDetailDao, IndentDetailEntity> implements IndentDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndentDetailEntity> page = this.page(
                new Query<IndentDetailEntity>().getPage(params),
                new QueryWrapper<IndentDetailEntity>()
        );

        return new PageUtils(page);
    }

}