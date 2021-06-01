package com.graduation.ylservice.service.impl;

import com.graduation.ylservice.dao.AccountDao;
import com.graduation.ylservice.dao.DishDao;
import com.graduation.ylservice.dao.IndentDao;
import com.graduation.ylservice.entity.AccountEntity;
import com.graduation.ylservice.entity.DishEntity;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.entity.query.IndentDetailResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.IndentDetailDao;
import com.graduation.ylservice.entity.IndentDetailEntity;
import com.graduation.ylservice.service.IndentDetailService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("indentDetailService")
public class IndentDetailServiceImpl extends ServiceImpl<IndentDetailDao, IndentDetailEntity> implements IndentDetailService {

    @Autowired
    private IndentDetailDao indentDetailDao;

    @Autowired
    private IndentDao indentDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private AccountDao accountDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndentDetailEntity> page = this.page(
                new Query<IndentDetailEntity>().getPage(params),
                new QueryWrapper<IndentDetailEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 通过订单id 先查询该订单的所有子表数据
     * 再查询菜品的照片和单价
     * 封装到indentDetailResult的list集合返回给前端
     *
     * @param deIndentId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<IndentDetailResult> selectDetailById(String deIndentId) {
        //取得该订单号下的所有订单子表信息
        IndentDetailEntity detailEntity = new IndentDetailEntity();
        detailEntity.setDeIndentId(deIndentId);
        List<IndentDetailEntity> indentDetailList = indentDetailDao.selectList(new QueryWrapper<IndentDetailEntity>(detailEntity));
        //取得菜品的对应菜品名称、照片信息
        List<IndentDetailResult> indentDetailResults = new LinkedList<>();
        for (IndentDetailEntity indentDetailEntity : indentDetailList) {
            IndentDetailResult detailResult = new IndentDetailResult();
            detailResult.setIndentId(deIndentId);
            detailResult.setDishId(indentDetailEntity.getDeDishId());
            detailResult.setNum(indentDetailEntity.getNum());
            //根据菜品id查询菜品信息
            DishEntity dishEntity = dishDao.selectById(indentDetailEntity.getDeDishId());
            detailResult.setDishName(dishEntity.getDishName());
            detailResult.setDishPicture(dishEntity.getDishPicture());
            detailResult.setDishPrice(dishEntity.getDishPrice());
            //存入list
            indentDetailResults.add(detailResult);
        }
        return indentDetailResults;
    }

     /**
     * 根据订单id和菜品id
     * 1.删除指定订单子表内容
     * 2.更新订单数据
     * 3.判断子表内容 若为0 删除该订单
     *
     * @param indentId
     * @param dishId
     * @return
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean removeByDishId(String indentId, String dishId) {
        //1.根据条件查询订单内容
        QueryWrapper<IndentDetailEntity> indentDetailQueryWrapper = new QueryWrapper<IndentDetailEntity>()
                .eq("de_indent_id", indentId)
                .eq("de_dish_id", dishId);
        //2.更新订单表信息 --> 订单菜品数 + 订单总金额
        //查订单表 订单子表 菜品表对应数据
        IndentDetailEntity indentDetail = indentDetailDao.selectOne(indentDetailQueryWrapper);
        IndentEntity indent = indentDao.selectById(indentId);
        DishEntity dish = dishDao.selectById(dishId);
        System.out.println("indent" + indent);
        System.out.println("indentDetail" + indentDetail);
        //记录订单还剩多少件菜品
        int lastTotal = indent.getDishTotal() - indentDetail.getNum();
        indent.setDishTotal(lastTotal); //更新订单菜品总数量
        indent.setTotal(indent.getTotal() - indentDetail.getNum() * dish.getDishPrice()); //更新订单总金额
        //TODO 根据订单支付方式 决定是向支付宝还是用户充值表返回金额
        //不论支付方式 直接退回到余额里
        AccountEntity account = accountDao.selectOne(new QueryWrapper<AccountEntity>().eq("ac_user_id",indent.getInUserId()));
        account.setAmount(account.getAmount() + dish.getDishPrice() * indentDetail.getNum());
        accountDao.updateById(account);
        if (lastTotal == 0) { //订单无内容
            indentDao.deleteById(indentId);
        }else{ //还有菜品
            indentDao.updateById(indent);
        }
        //3.删除订单子表对应数据
        int result = indentDetailDao.delete(indentDetailQueryWrapper);
        return result == 1;
    }

}