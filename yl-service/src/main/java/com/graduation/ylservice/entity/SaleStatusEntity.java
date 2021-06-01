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
@TableName("sale_status")
public class SaleStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜品售出状态主键，唯一标识
	 */
	@TableId(type = IdType.ID_WORKER_STR)
	private String saleStatusId;
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
	 * 菜品售出状态（0售完，1未售完）
	 */
	private String saleStatus;

}
