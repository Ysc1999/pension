package com.graduation.ylservice.service.impl;

import com.alipay.api.domain.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.ylservice.dao.AccountDao;
import com.graduation.ylservice.dao.UserDao;
import com.graduation.ylservice.entity.AccountEntity;
import com.graduation.ylservice.entity.UserEntity;
import com.graduation.ylservice.entity.query.IndentQuery;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;

import com.graduation.ylservice.dao.IndentDao;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.service.IndentService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("indentService")
public class IndentServiceImpl extends ServiceImpl<IndentDao, IndentEntity> implements IndentService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    public IndentDao indentDao;

    static List<UserEntity> userEntities;

    static String[] address = new String[5];
    ;

    static {
        //返回user表全部数据
        //userEntities = userDao.selectList(null);

        //创建地址数据 随机选择
        address[0] = "河北区";
        address[1] = "和平区";
        address[2] = "河东区";
        address[3] = "河西区";
        address[4] = "南开区";
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IndentEntity> page = this.page(
                new Query<IndentEntity>().getPage(params),
                new QueryWrapper<IndentEntity>().eq("is_payed",(Integer.parseInt((String)(params.get("isPayed")))))
        );

        return new PageUtils(page);
    }

    /**
     * 创建虚拟数据 根据user数据表的数据 随机选择一个用户 形成订单数据
     *
     * @param indent
     */
    @Override
    public void saveIndent(IndentEntity indent) {
        userEntities = userDao.selectList(null);
        int index = (int) Math.round(Math.random() * 5);
        UserEntity entiy = userEntities.get(index);

        //存放订单的用户
        indent.setInUserId(entiy.getUserId());
        indent.setInUserName(entiy.getUserName());
        indent.setInUserAddress(address[index]);
        indent.setInUserPhone(entiy.getUserPhone());

        indentDao.insert(indent);
    }

    /**
     * 根据参数查询订单结果并分页
     *
     * @param indentQuery
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public PageUtils selectByParams(IndentQuery indentQuery) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", indentQuery.getPage());
        params.put("limit", indentQuery.getLimit());
        IPage<IndentEntity> page = new Query<IndentEntity>().getPage(params);
        //分页查询
        List<IndentEntity> indentEntities = indentDao.selectIndentByParams(page, indentQuery);
        //存入page中 计算总数据量、总页面等数据
        page.setRecords(indentEntities);

        return new PageUtils(page);
    }

    /**
     * 账户余额支付订单
     * @param indentId
     * @param total
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean payByAccount(String indentId, float total) {
        //拿订单id找用户id
        IndentEntity indent = indentDao.selectById(indentId);
        String uId = indent.getInUserId();
        AccountEntity accountEntity = accountDao.selectOne(new QueryWrapper<AccountEntity>().eq("ac_user_id",uId));
        /**
         * 通过余额判断是否能够支付
         */
        if(accountEntity.getAmount() >= total){
            //1.更新账户余额
            accountEntity.setAcUserId(uId);
            accountEntity.setAmount(accountEntity.getAmount() - total);
            accountDao.updateById(accountEntity);
            //2.更新订单状态
            IndentEntity indentEntity = new IndentEntity();
            indentEntity.setIndentId(indentId);
            indentEntity.setPayStatus(0);
            indentEntity.setPayTime(new Date());
            indentEntity.setIsPayed(1);
            indentDao.updateById(indentEntity);
            return true;
        }
        return false;
    }

}