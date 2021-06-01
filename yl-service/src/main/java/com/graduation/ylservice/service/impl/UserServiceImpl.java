package com.graduation.ylservice.service.impl;

import com.alibaba.nacos.client.config.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.UserDao;
import com.graduation.ylservice.entity.UserEntity;
import com.graduation.ylservice.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 检查用户登录信息
     * @return
     */
    @Override
    public boolean checkLogin(UserEntity user, HttpServletRequest request) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        //根据用户id查询结果
        UserEntity entity = userDao.selectOne(queryWrapper.eq("user_name",user.getUserName()));
        if(null == entity){
            return false;
        }
        //MD5加密对比数据库数据
        MD5 md5Utils = MD5.getInstance();
        String password = md5Utils.getMD5String(user.getUserPassword());
        if(!password.equals(entity.getUserPassword())){
            return false;
        }
        return true;
    }

}