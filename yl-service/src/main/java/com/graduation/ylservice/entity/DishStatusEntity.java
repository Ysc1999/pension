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
@TableName("dish_status")
public class DishStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品上架状态id
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String stDishId;
	/**
	 * 菜品状态（0下架，1上架）
	 */
	private String dishStatus;
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

}
