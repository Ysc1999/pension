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
@TableName("rider")
public class RiderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 骑手表主键，唯一标识
	 */
	@TableId
	private String pkRiderId;
	/**
	 * 骑手名称，可以重复
	 */
	private String riderName;
	/**
	 * 骑手编号，唯一标识
	 */
	private String nkRiderNo;
	/**
	 * 骑手密码
	 */
	private String riderPassword;
	/**
	 * 骑手头像，可以为null
	 */
	private String riderAvater;
	/**
	 * 骑手电话，唯一标识
	 */
	private String nkRiderPhone;
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
