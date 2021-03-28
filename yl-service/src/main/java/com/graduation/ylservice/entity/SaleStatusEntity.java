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
@TableName("sale_status")
public class SaleStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品ID
	 */
	@TableId
	private String saleDishId;
	/**
	 * 菜品状态（0售完，1未售完）
	 */
	private Integer saleStatus;
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
