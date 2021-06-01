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
@TableName("indent_detail")
public class IndentDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId(value = "de_indent_id",type = IdType.ID_WORKER_STR)
	private String deIndentId;
	/**
	 * 菜品ID
	 */
	private String deDishId;
	/**
	 * 单品数目
	 */
	private Integer num;
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
