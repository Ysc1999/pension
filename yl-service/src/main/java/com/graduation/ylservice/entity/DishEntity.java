package com.graduation.ylservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Yourself
 * @email NOPE@gmail.com
 * @date 2021-03-27 22:58:44
 */
@Data
@TableName("dish")
public class DishEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品ID
	 */
	@TableId
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
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 变更时间
	 */
	private Date gmtModified;

}
