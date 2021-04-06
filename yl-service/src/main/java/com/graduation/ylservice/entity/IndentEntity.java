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
@TableName("indent")
public class IndentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId(value = "indent_id",type = IdType.ID_WORKER_STR)
	private String indentId;
	/**
	 * 用户ID
	 */
	private String inUserId;
	/**
	 * 支付方式(0 账户付款 1 支付宝)
	 */
	private Integer payStatus;
	/**
	 * 用户姓名
	 */
	private String inUserName;
	/**
	 * 用户住址
	 */
	private String inUserAddress;
	/**
	 * 用户手机号
	 */
	private String inUserPhone;
	/**
	 * 总金额
	 */
	private Float total;
	/**
	 * 逻辑删除，1删除，0未删除
	 */
	@TableLogic
	private Integer isDelete;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 到货时间
	 */
	private Date arrayTime;
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
	 * 是否支付
	 */
	private Integer isPayed;
	/**
	 * 乐观锁
	 */
	@Version
	private Integer version;
	/**
	 * 订单选定的菜单数量
	 */
	private Integer dishTotal;

	public IndentEntity() {
	}
}
