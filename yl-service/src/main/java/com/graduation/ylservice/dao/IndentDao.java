package com.graduation.ylservice.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.ylservice.entity.IndentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.ylservice.entity.query.IndentQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-04-06 11:15:25
 */
@Mapper
public interface IndentDao extends BaseMapper<IndentEntity> {

    List<IndentEntity> selectIndentByParams(IPage<IndentEntity> page, @Param("indentQuery")IndentQuery indentQuery);
}
