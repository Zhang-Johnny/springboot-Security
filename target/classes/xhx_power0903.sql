/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : xhm_power

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 29/08/2018 16:57:50
*/
CREATE DATABASE `xhx_power` DEFAULT CHARACTER SET utf8;

USE `xhx_power`;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xhx_department
-- ----------------------------
DROP TABLE IF EXISTS `xhx_department`;
CREATE TABLE `xhx_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL,
  `depPath` varchar(255) DEFAULT NULL,
  `enabled` int(1) DEFAULT '1',
  `isParent` int(1) DEFAULT '0',
  `createTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xhx_group_user
-- ----------------------------
DROP TABLE IF EXISTS `xhx_group_user`;
CREATE TABLE `xhx_group_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位公司',
  `mobilePhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `bindMobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户绑定手机',
  `lastLoginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录总次数',
  `profilePicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xhx_invoice_aptitude
-- ----------------------------
DROP TABLE IF EXISTS `xhx_invoice_aptitude`;
CREATE TABLE `xhx_invoice_aptitude`  (
  `id` bigint(20) NOT NULL,
  `userId` int(20) NOT NULL,
  `accountName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户名称',
  `taxNumber` int(20) NOT NULL COMMENT '税务号',
  `context` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票内容',
  `bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户银行',
  `bankNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行账号',
  `retinfoAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册地址',
  `mailingAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮寄地址',
  `addressee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '增票资质' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xhx_invoice_order
-- ----------------------------
DROP TABLE IF EXISTS `xhx_invoice_order`;
CREATE TABLE `xhx_invoice_order`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `inaptitudeId` bigint(20) NULL DEFAULT NULL COMMENT '资质ID',
  `invoiceno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票号码',
  `invoiceHeader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票抬头',
  `taxNumber` int(20) NOT NULL COMMENT '税务号',
  `orderType` int(1) NOT NULL COMMENT '订单类型',
  `invoiceType` int(1) NOT NULL COMMENT '发票类型:\r\n0 普通发票\r\n1 增值税发票',
  `invoiceNature` int(1) NOT NULL COMMENT '发票性值\r\n0 纸质\r\n1 电子',
  `orderAmount` double(2, 0) NOT NULL COMMENT '发票金额',
  `orderStatus` int(1) NOT NULL COMMENT '发票状态\r\n0、未开发票 1、已开发票',
  `merchandiseid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务订单Id',
  `context` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票内容',
  `deliveryType` int(1) NOT NULL COMMENT '配送方式：0 快递1 下载',
  `mailingAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮寄地址',
  `addressee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `orderTime` datetime(0) NOT NULL COMMENT '申请时间',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xhx_merchandise_order
-- ----------------------------
DROP TABLE IF EXISTS `xhx_merchandise_order`;
CREATE TABLE `xhx_merchandise_order`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `orderName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单名称',
  `orderType` int(1) NOT NULL COMMENT '订单类型',
  `orderAmount` double(2, 0) NOT NULL COMMENT '订单金额',
  `invoiceStatus` int(1) NOT NULL COMMENT '0、未开发票 1、已开发票/无余额2、已开发票/有余额\r\n',
  `invoiceBalance` double(2, 0) NULL DEFAULT NULL COMMENT '发票余额',
  `orderContext` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单内容',
  `orderTime` datetime(0) NOT NULL COMMENT '订购时间',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `xhx_menu`;
CREATE TABLE `xhx_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` int(1) DEFAULT NULL,
  `requireAuth` int(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` int(1) DEFAULT '1',
  `createTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `xhx_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for xhx_re_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `xhx_re_role_menu`;
CREATE TABLE `xhx_re_role_menu`  (
  `id` bigint(20) NOT NULL,
  `mId` bigint(20) NOT NULL COMMENT '模块ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_danish_ci COMMENT = '角色权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xhx_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `xhx_re_user_role`;
CREATE TABLE `xhx_re_user_role`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_danish_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xhx_role
-- ----------------------------
DROP TABLE IF EXISTS `xhx_role`;
CREATE TABLE `xhx_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `nameZh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文',
	`sort` int(11) NOT NULL COMMENT '排序',
  `isEnable` int(1) DEFAULT '1',
  `createTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xhx_user
-- ----------------------------
DROP TABLE IF EXISTS `xhx_user`;
CREATE TABLE `xhx_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码(加密)',
  `parentId` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `userType` int(1) NOT NULL COMMENT '用户类型',
  `bindMobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户所绑定的手机号',
  `lastLoginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NOT NULL COMMENT '登录总次数',
  `profilePicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xhx_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `xhx_userinfo`;
CREATE TABLE `xhx_userinfo`  (
  `id` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `parentId` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `userType` int(1) NOT NULL COMMENT '用户类型',
  `nameZh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户中文名称',
  `nameEn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文名称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mobilePhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通信地址',
  `birthDay` date NOT NULL COMMENT '出生日期',
  `sex` int(1) NOT NULL COMMENT '性别,1男，2女，3其他',
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位公司',
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门',
  `companyid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司ID',
  `occupation` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业/职业',
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户详情' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `xhx_department` VALUES ('1', '股东会', '-1', '.1', '1', '1',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('4', '董事会', '1', '.1.4', '1', '1',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('5', '总办', '4', '.1.4.5', '1', '1',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('8', '财务部', '5', '.1.4.5.8', '1', '0',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('78', '销售部', '5', '.1.4.5.78', '1', '1',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('91', '技术部', '5', '.1.4.5.91', '1', '0',NULL,NULL);
INSERT INTO `xhx_department` VALUES ('92', '运维部', '5', '.1.4.5.92', '1', '0',NULL,NULL);
-- ----------------------------
-- Records of xhx_menu
-- ----------------------------
INSERT INTO `xhx_menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `xhx_menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', null, '1', '1', '1');
INSERT INTO `xhx_menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, '1', '1', '1');
INSERT INTO `xhx_menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, '1', '1', '1');
INSERT INTO `xhx_menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, '1', '1', '1');
INSERT INTO `xhx_menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, '1', '1', '1');
INSERT INTO `xhx_menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', null, null, '1', '2', '1');
INSERT INTO `xhx_menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', null, null, '1', '2', '0');
INSERT INTO `xhx_menu` VALUES ('9', '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', null, null, '1', '3', '0');
INSERT INTO `xhx_menu` VALUES ('10', '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', null, null, '1', '3', '1');
INSERT INTO `xhx_menu` VALUES ('11', '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', null, null, '1', '3', '1');
INSERT INTO `xhx_menu` VALUES ('12', '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', null, null, '1', '3', '1');
INSERT INTO `xhx_menu` VALUES ('13', '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', null, null, '1', '3', '1');
INSERT INTO `xhx_menu` VALUES ('14', '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', null, null, '1', '4', '1');
INSERT INTO `xhx_menu` VALUES ('15', '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', null, null, '1', '4', '1');
INSERT INTO `xhx_menu` VALUES ('16', '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', null, null, '1', '4', '1');
INSERT INTO `xhx_menu` VALUES ('17', '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', null, null, '1', '4', '1');
INSERT INTO `xhx_menu` VALUES ('18', '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', null, null, '1', '4', '1');
INSERT INTO `xhx_menu` VALUES ('19', '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', null, null, '1', '5', '1');
INSERT INTO `xhx_menu` VALUES ('20', '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', null, null, '1', '5', '1');
INSERT INTO `xhx_menu` VALUES ('21', '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', null, null, '1', '5', '1');
INSERT INTO `xhx_menu` VALUES ('22', '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', null, null, '1', '5', '1');
INSERT INTO `xhx_menu` VALUES ('23', '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', null, null, '1', '6', '1');
INSERT INTO `xhx_menu` VALUES ('24', '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', null, null, '1', '6', '1');
INSERT INTO `xhx_menu` VALUES ('25', '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', null, null, '1', '6', '1');
INSERT INTO `xhx_menu` VALUES ('26', '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', null, null, '1', '6', '1');
INSERT INTO `xhx_menu` VALUES ('27', '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', null, null, '1', '6', '1');
INSERT INTO `xhx_menu` VALUES ('28', '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', null, null, '1', '6', '1');
-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `xhx_re_role_menu` VALUES ('161', '7', '3');
INSERT INTO `xhx_re_role_menu` VALUES ('162', '7', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('163', '9', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('164', '10', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('165', '11', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('166', '12', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('167', '13', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('168', '14', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('169', '15', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('170', '16', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('171', '17', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('172', '18', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('173', '19', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('174', '20', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('175', '21', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('176', '22', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('177', '23', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('178', '25', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('179', '26', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('180', '27', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('181', '28', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('182', '24', '6');
INSERT INTO `xhx_re_role_menu` VALUES ('247', '7', '4');
INSERT INTO `xhx_re_role_menu` VALUES ('248', '8', '4');
INSERT INTO `xhx_re_role_menu` VALUES ('249', '11', '4');
INSERT INTO `xhx_re_role_menu` VALUES ('250', '7', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('251', '8', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('252', '9', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('253', '10', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('254', '12', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('255', '13', '2');
INSERT INTO `xhx_re_role_menu` VALUES ('256', '7', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('257', '8', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('258', '9', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('259', '10', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('260', '11', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('261', '12', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('262', '13', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('263', '14', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('264', '15', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('265', '16', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('266', '17', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('267', '18', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('268', '19', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('269', '20', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('270', '21', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('271', '22', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('272', '23', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('273', '24', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('274', '25', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('275', '26', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('276', '27', '1');
INSERT INTO `xhx_re_role_menu` VALUES ('277', '28', '1');

-- ----------------------------
-- Records of xhx_role
-- ----------------------------
INSERT INTO `xhx_role` VALUES ('1', 'ROLE_manager', '部门经理',1,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('2', 'ROLE_personnel', '人事专员',2,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('3', 'ROLE_recruiter', '招聘主管',3,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('4', 'ROLE_train', '培训主管',4,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('5', 'ROLE_performance', '薪酬绩效主管',8,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('6', 'ROLE_admin', '系统管理员',5,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('13', 'ROLE_test2', '测试角色2',6,1,NULL,NULL);
INSERT INTO `xhx_role` VALUES ('14', 'ROLE_test1', '测试角色1',7,1,NULL,NULL);


USE `xhx_power`;
CREATE TABLE `xhx_user_visit_api`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位公司',
  `loginCount` int(11) NOT NULL COMMENT '访问总次数',
	`isEnable` int(1) NOT NULL COMMENT '是否启用',
	`loginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户IP',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户调用API表' ROW_FORMAT = Dynamic;


USE `xhx_power`;
CREATE TABLE `xhx_white_list`  (
  `id` bigint(20) NOT NULL,
	`loginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户IP',
  `createTime` datetime(0) NOT NULL,
  `updateTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户调用API表' ROW_FORMAT = Dynamic;



CREATE TABLE `xhx_new_risk_tip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(32) DEFAULT NULL COMMENT '公司名称',
  `userId` int(20) NOT NULL,
  `riskTipDate` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `riskTipItem` varchar(100) DEFAULT NULL COMMENT '风险提示项目',
  `createTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
)