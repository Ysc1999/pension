package com.graduation.ylservice.controller.frontController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.config.AlipayConfig;
import com.graduation.ylservice.entity.IndentEntity;
import com.graduation.ylservice.entity.query.PayAsyncVo;
import com.graduation.ylservice.entity.query.PayVo;
import com.graduation.ylservice.service.IndentService;
import com.graduation.ylservice.utils.R;
import javafx.beans.binding.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 生成支付订单
 */
@RequestMapping("/ylservice/payConfig")
@Controller
public class PayController {

    @Autowired
    private IndentService indentService;

    @Autowired
    private AlipayConfig alipayConfig;

    @ResponseBody
    @GetMapping("/payByAccount/{indentId}/{total}")
    public R payByAccount(@PathVariable String indentId,@PathVariable float total){
        boolean result = indentService.payByAccount(indentId,total);
        return result ? R.ok() : R.error("账户余额不足，请充值或者支付宝支付");
    }

    /**
     * 支付宝提交订单
     * @param indent
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @PostMapping(value = "pay", produces = "text/html")
    public String pay(@RequestBody IndentEntity indent) throws AlipayApiException {
        PayVo payVo = new PayVo();
        //订单号
        payVo.setOut_trade_no(indent.getIndentId());
        payVo.setBody("养老服务餐厅为您服务");
        //订单描述
        payVo.setSubject("提交订单，准备配送");
        //订单金额
        payVo.setTotal_amount(indent.getTotal().toString());
        return alipayConfig.pay(payVo);
    }

    /**
     * 完成支付 修改订单状态
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/payRecord")
    public String payRecord(/*PayAsyncVo payAsyncVo,*/ HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        //从流水单中抽取订单id号 修改订单状态 未支付--->已支付
        String[] indentIds = request.getParameterMap().get("out_trade_no");
        String indentId = indentIds[0];
        IndentEntity indent = new IndentEntity();
        indent.setIndentId(indentId);
        indent.setIsPayed(1);
        indent.setPayTime(new Date());
        indent.setPayStatus(1);
        return indentService.updateById(indent) ? "success" : "false";
    }
}
