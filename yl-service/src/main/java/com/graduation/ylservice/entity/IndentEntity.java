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
@TableName("indent")
public class IndentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单ID
	 */
	@TableId
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
	private Date gmtCreate;
	/**
	 * 变更时间
	 */
	private Date gmtModified;

}
