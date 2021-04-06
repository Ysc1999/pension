/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50629
Source Host           : localhost:3306
Source Database       : ylservice

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2021-04-06 21:03:34
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
  PRIMARY KEY (`account_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('11', '1', '2.00', '0', '2021-03-02 21:21:22', '2021-04-02 21:46:39');

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
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  `dish_sale_status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜品售出状态',
  `dish_status` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜品上架状态',
  PRIMARY KEY (`dish_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES ('1379081075866832898', '1378987462465998850', '云顶三拼', '11.59', 'https://qcloud.dpfile.com/pc/5N-LYmlRhJqgRt-N2hf5r8QA3RKuI-xWkJxkBdhEH5oHUaF2sUpFo7RjityyGHw9l0cm-Lf9tDMlLZpO7rb3bg.jpg', '云顶三拼-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:45', '2021-04-05 22:38:45', '0', '0');
INSERT INTO `dish` VALUES ('1379081077531971585', '1378987462465998850', '家烧黄花鱼', '30.88', 'https://qcloud.dpfile.com/pc/kteGYhiMQGUEQwo0atLf8bCfjzHF0hJ3W-8PBEJONusPQPtvjMk22EcUeeOIT6okl0cm-Lf9tDMlLZpO7rb3bg.jpg', '家烧黄花鱼-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:46', '2021-04-05 22:38:46', '0', '0');
INSERT INTO `dish` VALUES ('1379081077599080449', '1378987462465998850', '台湾三杯鸡', '84.69', 'https://qcloud.dpfile.com/pc/0zt3olrmSWfvOKF4-mJmVsyUXsfdRSG7uMRG6zexTjygaCxVp6XodL5UCwTJqJ9gl0cm-Lf9tDMlLZpO7rb3bg.jpg', '台湾三杯鸡-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:46', '2021-04-05 22:38:46', '0', '0');
INSERT INTO `dish` VALUES ('1379081077599080450', '1378987462465998850', '台湾油煎肝', '73.57', 'https://qcloud.dpfile.com/pc/V3AQqLLILl69MlbeByzMe61e8qSrJdm-PurQ-oeSKPCE1dUpvbW1gcNw26qKBJshl0cm-Lf9tDMlLZpO7rb3bg.jpg', '台湾油煎肝-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:46', '2021-04-05 22:38:46', '0', '0');
INSERT INTO `dish` VALUES ('1379081077599080451', '1378987462465998850', '三色山药泥', '54.22', 'https://qcloud.dpfile.com/pc/CpRjsQvNYti9Pydj0BSx12opicHqCVLSPQLKfNnm1eS694TLIQ2LCj8DADdqcuZEl0cm-Lf9tDMlLZpO7rb3bg.jpg', '三色山药泥-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:46', '2021-04-05 22:38:46', '0', '0');
INSERT INTO `dish` VALUES ('1379081077670383618', '1378987462465998850', '台北肉燥饭', '16.32', 'https://qcloud.dpfile.com/pc/ph9-nDyr1AYRC8pxyUiZgeGuUobd3ruXag7v7N-qNVoy1WEsxPgkCs9TFktFM1N3l0cm-Lf9tDMlLZpO7rb3bg.jpg', '台北肉燥饭-宝月楼·台湾菜馆', '0', '2021-04-05 22:38:46', '2021-04-05 22:38:46', '0', '0');
INSERT INTO `dish` VALUES ('1379081602797154306', '1378987462465998850', '美点拼盘', '97.38', 'https://qcloud.dpfile.com/pc/RRCFjtlXEjoizYvgQGGbRtZJ_LHT5SNO5pohkcqhMVDJVC6bnk8gaYpElMMrbfnll0cm-Lf9tDMlLZpO7rb3bg.jpg', '美点拼盘-宝月楼·台湾菜馆', '0', '2021-04-05 22:40:51', '2021-04-05 22:40:51', '0', '0');
INSERT INTO `dish` VALUES ('1379081603019452418', '1378987462465998850', '梅菜扣肉', '51.91', 'https://qcloud.dpfile.com/pc/jcAvQNiAtmzbPDJlK6hGJzXjGCUnzMwZ-mtTZ2a1iYl5-7kWX-MRGqq_dKgBXkTcl0cm-Lf9tDMlLZpO7rb3bg.jpg', '梅菜扣肉-宝月楼·台湾菜馆', '0', '2021-04-05 22:40:51', '2021-04-05 22:40:51', '0', '0');
INSERT INTO `dish` VALUES ('1379081603073978370', '1378987462465998850', '沙律芥香虾', '54.16', 'https://qcloud.dpfile.com/pc/1qJ5kTE5hauhEciF_ix9mhBGKscIrnxQZP8DdWPE-3VMqKUPnvbfbOY6kUekmg3Il0cm-Lf9tDMlLZpO7rb3bg.jpg', '沙律芥香虾-宝月楼·台湾菜馆', '0', '2021-04-05 22:40:51', '2021-04-05 22:40:51', '0', '0');
INSERT INTO `dish` VALUES ('1379081925154697218', '1378987462465998850', '台式风味茄', '45.15', 'https://qcloud.dpfile.com/pc/tdM4CCmLEvW0UdwijmYT2jLuDo8o2qd9o1J0m5APgg12SQfVv-GUKZxeoM-v1_arl0cm-Lf9tDMlLZpO7rb3bg.jpg', '台式风味茄-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:08', '2021-04-05 22:42:08', '0', '0');
INSERT INTO `dish` VALUES ('1379081925217611777', '1378987462465998850', '糖醋小排', '99.64', 'https://qcloud.dpfile.com/pc/MHRk6u4zHrN3v4KRuungcA53YIZNABNJbyryyhKHpdXMzobgt4KobVBbD3pn8Hqxl0cm-Lf9tDMlLZpO7rb3bg.jpg', '糖醋小排-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:08', '2021-04-05 22:42:08', '0', '0');
INSERT INTO `dish` VALUES ('1379081925427326977', '1378987462465998850', '台式鹅肝冻', '4.40', 'https://qcloud.dpfile.com/pc/yxMyOmEP04VWogwVrKptd0vtXJSbYRSp86lD1atcRqHC3wLLkQSYRd-QXEutyGfnl0cm-Lf9tDMlLZpO7rb3bg.jpg', '台式鹅肝冻-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:08', '2021-04-05 22:42:08', '0', '0');
INSERT INTO `dish` VALUES ('1379081925490241537', '1378987462465998850', '三杯鸡', '61.99', 'https://qcloud.dpfile.com/pc/aAWK8lXPdX9V0hRb44AQh-wq97Zq1L48tQQFagmUCliyZslx_GVxXaEoqZmBGnuul0cm-Lf9tDMlLZpO7rb3bg.jpg', '三杯鸡-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:08', '2021-04-05 22:42:08', '0', '0');
INSERT INTO `dish` VALUES ('1379082127911432193', '1378987462465998850', '牛眼贝鲜虾萝卜丝', '20.98', 'https://qcloud.dpfile.com/pc/JfKiRxriXQit8KYwllIoFCODqjoG63hrG8g5cCwfwBO97MeSLYiTrOPJtpNzNiTol0cm-Lf9tDMlLZpO7rb3bg.jpg', '牛眼贝鲜虾萝卜丝-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:56', '2021-04-05 22:42:56', '0', '0');
INSERT INTO `dish` VALUES ('1379082127995318273', '1378987462465998850', '澎湖湾外婆鱼', '59.22', 'https://qcloud.dpfile.com/pc/a9gcC7QZGZZpWfmn5fqhGyppAbah-out2Os4LjdjjP0-1l6BLViGherA7p2LOYual0cm-Lf9tDMlLZpO7rb3bg.jpg', '澎湖湾外婆鱼-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:56', '2021-04-05 22:42:56', '0', '0');
INSERT INTO `dish` VALUES ('1379082128095981569', '1378987462465998850', '鱼籽鹅肝冻', '57.03', 'https://qcloud.dpfile.com/pc/WbQF8liKpwPGHVkrkgm5ejDGhIagniZaDQeAtHuRZZ6QwhMdSqiBQXpCemwnGxkol0cm-Lf9tDMlLZpO7rb3bg.jpg', '鱼籽鹅肝冻-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:56', '2021-04-05 22:42:56', '0', '0');
INSERT INTO `dish` VALUES ('1379082128167284738', '1378987462465998850', '布农腌香猪肘', '0.86', 'https://qcloud.dpfile.com/pc/xbYFAljp0wcq0NriWO4IWJp1xWSw5IruGbNYbINrkEA1jxyfAGZJzVglGXWdLx0kl0cm-Lf9tDMlLZpO7rb3bg.jpg', '布农腌香猪肘-宝月楼·台湾菜馆', '0', '2021-04-05 22:42:56', '2021-04-05 22:42:56', '0', '0');

-- ----------------------------
-- Table structure for `dish_kind`
-- ----------------------------
DROP TABLE IF EXISTS `dish_kind`;
CREATE TABLE `dish_kind` (
  `kind_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品种类ID',
  `kind_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品种类名称',
  `is_delete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`kind_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dish_kind
-- ----------------------------
INSERT INTO `dish_kind` VALUES ('1378987462465998850', '凉菜', '0', '2021-04-05 16:26:46', '2021-04-05 16:26:46');
INSERT INTO `dish_kind` VALUES ('1378987510700494850', '热菜', '0', '2021-04-05 16:26:58', '2021-04-05 16:26:58');
INSERT INTO `dish_kind` VALUES ('1378987550458302466', '主食', '0', '2021-04-05 16:27:07', '2021-04-05 16:27:07');
INSERT INTO `dish_kind` VALUES ('1378987583945625602', '汤品', '0', '2021-04-05 16:27:15', '2021-04-05 16:27:15');
INSERT INTO `dish_kind` VALUES ('1378987752279822338', '小吃', '0', '2021-04-05 16:27:55', '2021-04-05 16:27:55');

-- ----------------------------
-- Table structure for `dish_status`
-- ----------------------------
DROP TABLE IF EXISTS `dish_status`;
CREATE TABLE `dish_status` (
  `st_dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品上架状态id',
  `dish_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品状态（0下架，1上架）',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`st_dish_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dish_status
-- ----------------------------
INSERT INTO `dish_status` VALUES ('1378622858820886529', '0', '0', '2021-04-04 16:17:58', '2021-04-04 16:17:58');
INSERT INTO `dish_status` VALUES ('1378622887333765121', '1', '0', '2021-04-04 16:18:05', '2021-04-04 16:18:05');

-- ----------------------------
-- Table structure for `indent`
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `indent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `in_user_id` char(19) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户ID',
  `pay_status` tinyint(1) DEFAULT '2' COMMENT '支付方式(0 账户付款 1 支付宝 2 未支付)',
  `in_user_name` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `in_user_address` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户住址',
  `in_user_phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `total` float(8,2) NOT NULL COMMENT '总金额',
  `is_delete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '逻辑删除，1删除，0未删除',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `array_time` datetime DEFAULT NULL COMMENT '到货时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '变更时间',
  `is_payed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否支付',
  `version` int(11) NOT NULL COMMENT '乐观锁',
  `dish_total` int(11) NOT NULL COMMENT '订单选定的菜单数量',
  PRIMARY KEY (`indent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of indent
-- ----------------------------
INSERT INTO `indent` VALUES ('1379086460098973697', '1378914156526211074', '2', '杨司成', '河北区', '18522006389', '158.68', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '4');
INSERT INTO `indent` VALUES ('1379251082366095361', '1378973972292472833', '2', '周梦雨', '南开区', '18604194861', '270.86', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '5');
INSERT INTO `indent` VALUES ('1379251104008704002', '1378973972292472833', '2', '周梦雨', '南开区', '18604194861', '180.60', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '4');
INSERT INTO `indent` VALUES ('1379251124808257537', '1378972016580792321', '2', '王庆周', '河东区', '13940561948', '177.66', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '3');
INSERT INTO `indent` VALUES ('1379251245734236162', '1378971827530928129', '2', '赵风佑', '和平区', '13518006497', '51.86', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '2');
INSERT INTO `indent` VALUES ('1379251277141184513', '1378972016580792321', '2', '王庆周', '河东区', '13940561948', '115.65', '0', null, null, '2021-04-06 09:55:04', '2021-04-06 15:27:34', '0', '0', '3');

-- ----------------------------
-- Table structure for `indent_detail`
-- ----------------------------
DROP TABLE IF EXISTS `indent_detail`;
CREATE TABLE `indent_detail` (
  `de_indent_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单ID',
  `de_dish_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品ID',
  `num` int(2) NOT NULL COMMENT '单品数目',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of indent_detail
-- ----------------------------
INSERT INTO `indent_detail` VALUES ('1379086460098973697', '1379081075866832898', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379086460098973697', '1379081077531971585', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379086460098973697', '1379081077599080451', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379086460098973697', '1379081925490241537', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251082366095361', '1379081603073978370', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251082366095361', '1379081603073978370', '3', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251082366095361', '1379081077599080451', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251104008704002', '1379081925154697218', '2', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251104008704002', '1379081925154697218', '2', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251124808257537', '1379082127995318273', '3', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251245734236162', '1379081077531971585', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251245734236162', '1379082127911432193', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251277141184513', '1379082128095981569', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251277141184513', '1379081077599080451', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');
INSERT INTO `indent_detail` VALUES ('1379251277141184513', '1379081925427326977', '1', '0', '2021-04-06 15:27:34', '2021-04-06 15:27:34');

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
  PRIMARY KEY (`PK_RIDER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of rider
-- ----------------------------

-- ----------------------------
-- Table structure for `sale_status`
-- ----------------------------
DROP TABLE IF EXISTS `sale_status`;
CREATE TABLE `sale_status` (
  `sale_status_id` char(19) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品售出状态主键，唯一标识',
  `is_delete` tinyint(1) NOT NULL COMMENT '逻辑删除，1删除，0未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '变更时间',
  `sale_status` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品售出状态（0售完，1未售完）',
  PRIMARY KEY (`sale_status_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sale_status
-- ----------------------------
INSERT INTO `sale_status` VALUES ('1378622266086039553', '0', '2021-04-04 16:15:36', '2021-04-04 16:15:36', '0');
INSERT INTO `sale_status` VALUES ('1378622407119511553', '0', '2021-04-04 16:16:10', '2021-04-04 16:16:10', '1');

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
  PRIMARY KEY (`PK_TASK_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

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
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1378914156526211074', '杨司成', '18', '1', '天津', '120101198704094815', '18522006389', '0', '2021-04-05 11:35:29', '2021-04-05 11:35:29');
INSERT INTO `user` VALUES ('1378971827530928129', '赵风佑', '19', '1', '河北省', '120105199408046481', '13518006497', '0', '2021-04-05 15:24:38', '2021-04-05 15:24:38');
INSERT INTO `user` VALUES ('1378972016580792321', '王庆周', '21', '1', '天津市', '120101099706074016', '13940561948', '0', '2021-04-05 15:25:23', '2021-04-05 15:25:23');
INSERT INTO `user` VALUES ('1378973555252826113', '李瑾嘉', '24', '0', '天津市', '120105199805014861', '18544006194', '0', '2021-04-05 15:31:30', '2021-04-05 15:31:30');
INSERT INTO `user` VALUES ('1378973972292472833', '周梦雨', '23', '1', '天津市', '120101199707063148', '18604194861', '0', '2021-04-05 15:33:10', '2021-04-05 15:33:10');
