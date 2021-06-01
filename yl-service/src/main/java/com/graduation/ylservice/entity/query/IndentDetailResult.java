package com.graduation.ylservice.entity.query;

import lombok.Data;

/**
 * 返回的订单详情信息
 */
@Data
public class IndentDetailResult {
    /**
     * 订单ID
     */
    private String indentId;
    /**
     * 菜品ID
     */
    private String dishId;
    /**
     * 单品数目
     */
    private Integer num;
    /**
     * 菜品名称
     */
    private String dishName;
    /**
     * 菜品图片
     */
    private String dishPicture;
    /**
     * 菜品单价
     */
    private Float dishPrice;
}
