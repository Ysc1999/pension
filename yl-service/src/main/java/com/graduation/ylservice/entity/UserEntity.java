package com.graduation.ylservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.ID_WORKER_STR;

/**
 * 
 * 
 * @author Ysc666
 * @email NOPE@gmail.com
 * @date 2021-03-28 19:45:47
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(value = "user_id",type = IdType.ID_WORKER_STR)
	private String userId;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 用户年龄
	 */
	private Integer userAge;
	/**
	 * 用户性别(0 女 1 男)
	 */
	private Integer userSex;
	/**
	 * 用户住址
	 */
	private String userAddress;
	/**
	 * 身份证号
	 */
	private String userIdentify;
	/**
	 * 用户手机号
	 */
	private String userPhone;
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
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}
