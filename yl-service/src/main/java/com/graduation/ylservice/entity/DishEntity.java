package com.graduation.ylservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@Data
@TableName("dish")
public class DishEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品ID
	 */
	@TableId(value = "dish_id",type = IdType.ID_WORKER_STR)
	private String dishId;
	/**
	 * 菜品种类名称
	 */
	private String dishKindName;
	/**
	 * 菜品名称
	 */
	private String dishName;
	/**
	 * 菜品单价
	 */
	private Float dishPrice;
	/**
	 * 菜品图片
	 */
	private String dishPicture;
	/**
	 * 详情描述
	 */
	private String description;
	/**
	 * 逻辑删除，1删除，0未删除
	 */
	@TableLogic
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
	/**
	 * 变更时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;
	/**
	 * 菜品售出状态
	 */
	private String dishSaleStatus;
	/**
	 * 菜品上架状态
	 */
	private String dishStatus;

}
