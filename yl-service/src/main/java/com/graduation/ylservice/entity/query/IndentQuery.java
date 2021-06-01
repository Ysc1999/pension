package com.graduation.ylservice.entity.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class IndentQuery {

    /**
     * 当前页面
     */
    private String page;
    /**
     * 每页显示条数
     */
    private String limit;
    /**
     * 下单用户名
     */
    private String inUserName;
    /**
     * 订单的起始时间
     */

    private Date beginTime;
    /**
     * 订单的截止时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    /**
     * 订单状态(0 已支付 1已下单 2 送货中 3 已完成)
     */
    private Integer indentStatus;

}
