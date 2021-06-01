package com.graduation.ylservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.ylservice.entity.query.IndentQuery;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.entity.IndentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
public interface IndentService extends IService<IndentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveIndent(IndentEntity indent);

    PageUtils selectByParams(IndentQuery indentQuery);

    boolean payByAccount(String indentId, float total);
}

