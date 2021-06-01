package com.graduation.ylservice.controller.frontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.service.IndentService;
import com.graduation.ylservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ylservice/indentFront")
public class IndentFrontController {

    @Autowired
    private IndentService indentService;

    @RequestMapping("/getIndent/{user_id}")
    public R getIndent(@PathVariable("user_id") String user_id){
        //根据用户id查询用户订单
        QueryWrapper<IndentEntity> queryWrapper = new QueryWrapper<IndentEntity>();
        queryWrapper.eq("in_user_id", user_id);
        //queryWrapper.eq("pay_status",1);  //修改订单的支付方式
        //queryWrapper.eq("is_payed",1);
        List<IndentEntity> list = indentService.list(queryWrapper);
        return R.ok().put("list",list);
    }

    @DeleteMapping("/deleteIndent/{indentId}")
    public R deleteIndent(@PathVariable String indentId){
        return indentService.removeById(indentId) ? R.ok() : R.error();
    }
}
