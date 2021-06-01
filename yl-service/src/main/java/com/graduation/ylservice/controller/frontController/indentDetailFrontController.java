package com.graduation.ylservice.controller.frontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.entity.IndentDetailEntity;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.entity.query.IndentDetailResult;
import com.graduation.ylservice.service.IndentDetailService;
import com.graduation.ylservice.service.IndentService;
import com.graduation.ylservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ylservice/indentDetailFront")
public class indentDetailFrontController {

    @Autowired
    private IndentService indentService;

    @Autowired
    private IndentDetailService indentDetailService;

    @RequestMapping("getIndentDetail/{indent_id}")
    public R getIndentDetail(@PathVariable("indent_id") String indent_id) {
        List<IndentDetailResult> list = indentDetailService.selectDetailById(indent_id);
        IndentEntity indent = indentService.getById(indent_id);
        return R.ok().put("list", list).put("indent",indent).put("total",indent.getDishTotal());
    }

}
