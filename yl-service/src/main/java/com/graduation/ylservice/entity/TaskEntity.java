package com.graduation.ylservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("task")
public class TaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 送餐表主键，唯一标识
	 */
	@TableId
	private String pkTaskId;
	/**
	 * 送餐编号，唯一标识
	 */
	private String nkTaskNo;
	/**
	 * 骑手表主键，唯一标识
	 */
	private String fkRiderId;
	/**
	 * 骑手名称，可以重复
	 */
	private String fkRiderName;
	/**
	 * 骑手编号，唯一标识
	 */
	private String fkRiderNo;
	/**
	 * 骑手电话，唯一标识
	 */
	private String fkRiderPhone;
	/**
	 * 订单表主键，唯一标识
	 */
	private String fkOrderId;
	/**
	 * 订单编号，唯一标识
	 */
	private String fkOrderNo;
	/**
	 * 菜品表主键，唯一标识
	 */
	private String fkDishId;
	/**
	 * 菜品编号，唯一标识
	 */
	private String fkDishNo;
	/**
	 * 菜品名称
	 */
	private String fkDishName;
	/**
	 * 送餐状态：0未配送，1配送中，2已送达
	 */
	private Integer taskStatus;
	/**
	 * 逻辑删除，1删除，0未删除
	 */
	private Integer deleteFlag;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 更新时间
	 */
	private Date gmtModified;

}
