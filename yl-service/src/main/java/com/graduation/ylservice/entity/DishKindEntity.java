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
@TableName("dish_kind")
public class DishKindEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品种类ID
	 */
	@TableId
	private String kindId;
	/**
	 * 菜品种类名称
	 */
	private String kindName;
	/**
	 * 逻辑删除，1删除，0未删除
	 */
	private Integer isDelete;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 更新时间
	 */
	private Date gmtModified;

}
