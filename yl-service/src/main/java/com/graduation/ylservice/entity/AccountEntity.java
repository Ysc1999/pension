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
@TableName("account")
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户ID
	 */
	@TableId
	private String accountId;
	/**
	 * 用户ID
	 */
	private String acUserId;
	/**
	 * 剩余金额
	 */
	private Float amount;
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
