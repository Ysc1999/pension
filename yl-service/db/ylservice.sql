/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : ylservice

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2021-03-28 10:42:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户ID',
  `ac_user_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `amount` float(8,2) NOT NULL COMMENT '剩余金额',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for `dish`
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品ID',
  `dish_kind_name` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品种类名称',
  `dish_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品名称',
  `dish_price` float(8,2) NOT NULL COMMENT '菜品单价',
  `dish_picture` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品图片',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详情描述',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dish
-- ----------------------------

-- ----------------------------
-- Table structure for `dish_kind`
-- ----------------------------
DROP TABLE IF EXISTS `dish_kind`;
CREATE TABLE `dish_kind` (
  `kind_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品种类ID',
  `kind_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品种类名称',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`kind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dish_kind
-- ----------------------------

-- ----------------------------
-- Table structure for `dish_status`
-- ----------------------------
DROP TABLE IF EXISTS `dish_status`;
CREATE TABLE `dish_status` (
  `st_dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品ID',
  `dish_status` tinyint(1) NOT NULL COMMENT '菜品状态（0下架，1上架）',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`st_dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dish_status
-- ----------------------------
INSERT INTO `dish_status` VALUES ('1000000000000000000', '1', '1', '2021-03-03 23:30:19', '2021-03-09 23:30:23');

-- ----------------------------
-- Table structure for `indent`
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `indent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `in_user_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户ID',
  `pay_status` tinyint(1) DEFAULT NULL COMMENT '支付方式(0 账户付款 1 支付宝)',
  `in_user_name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `in_user_address` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户住址',
  `in_user_phone` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户手机号',
  `total` float(8,2) DEFAULT NULL COMMENT '总金额',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `array_time` datetime DEFAULT NULL COMMENT '到货时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`indent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of indent
-- ----------------------------

-- ----------------------------
-- Table structure for `indent_detail`
-- ----------------------------
DROP TABLE IF EXISTS `indent_detail`;
CREATE TABLE `indent_detail` (
  `de_indent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `de_dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品ID',
  `num` int(2) NOT NULL COMMENT '单品数目',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of indent_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `rider`
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider` (
  `PK_RIDER_ID` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手表主键，唯一标识',
  `RIDER_NAME` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手名称，可以重复',
  `NK_RIDER_NO` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手编号，唯一标识',
  `RIDER_PASSWORD` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手密码',
  `RIDER_AVATER` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '骑手头像，可以为null',
  `NK_RIDER_PHONE` varchar(11) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手电话，唯一标识',
  `DELETE_FLAG` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `GMT_CREATE` datetime NOT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`PK_RIDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of rider
-- ----------------------------

-- ----------------------------
-- Table structure for `sale_status`
-- ----------------------------
DROP TABLE IF EXISTS `sale_status`;
CREATE TABLE `sale_status` (
  `sale_dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品ID',
  `sale_status` tinyint(1) NOT NULL COMMENT '菜品状态（0售完，1未售完）',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`sale_dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sale_status
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `PK_TASK_ID` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '送餐表主键，唯一标识',
  `NK_TASK_NO` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '送餐编号，唯一标识',
  `FK_RIDER_ID` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手表主键，唯一标识',
  `FK_RIDER_NAME` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手名称，可以重复',
  `FK_RIDER_NO` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手编号，唯一标识',
  `FK_RIDER_PHONE` varchar(11) CHARACTER SET utf8mb4 NOT NULL COMMENT '骑手电话，唯一标识',
  `FK_ORDER_ID` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '订单表主键，唯一标识',
  `FK_ORDER_NO` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '订单编号，唯一标识',
  `FK_DISH_ID` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '菜品表主键，唯一标识',
  `FK_DISH_NO` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '菜品编号，唯一标识',
  `FK_DISH_NAME` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '菜品名称',
  `TASK_STATUS` tinyint(1) NOT NULL COMMENT '送餐状态：0未配送，1配送中，2已送达',
  `DELETE_FLAG` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `GMT_CREATE` datetime NOT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`PK_TASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` char(19) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户ID',
  `user_name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `user_age` int(2) NOT NULL COMMENT '用户年龄',
  `user_sex` tinyint(1) NOT NULL COMMENT '用户性别(0 女 1 男)',
  `user_address` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户住址',
  `user_identify` varchar(18) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身份证号',
  `user_phone` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户手机号',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
