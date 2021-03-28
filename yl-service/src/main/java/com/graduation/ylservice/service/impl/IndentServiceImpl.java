package com.graduation.ylservice.service.impl;

import com.graduation.ylservice.dao.IndentDao;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.service.IndentService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("indentService")
public class IndentServiceImpl extends ServiceImpl<IndentDao, IndentEntity> implements IndentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndentEntity> page = this.page(
                new Query<IndentEntity>().getPage(params),
                new QueryWrapper<IndentEntity>()
        );

        return new PageUtils(page);
    }

}