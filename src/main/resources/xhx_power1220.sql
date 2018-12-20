/*
 Navicat Premium Data Transfer

 Source Server         : 123.59.198.72_3310
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : 123.59.198.72:3310
 Source Schema         : xhx_power

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 20/12/2018 14:19:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xhx_delivery_mailing_address
-- ----------------------------
DROP TABLE IF EXISTS `xhx_delivery_mailing_address`;
CREATE TABLE `xhx_delivery_mailing_address`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `personName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人名字',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在地区',
  `detailedAdd` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  `zipCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮编',
  `iphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `defaultStatus` int(1) NOT NULL COMMENT '启用状态：1 使用中 0未使用',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货邮寄地址' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_delivery_mailing_address
-- ----------------------------
INSERT INTO `xhx_delivery_mailing_address` VALUES (23170127147175931, 3, '赵六', '北京', '北京市,酒仙桥路', '1001', '111122', 0, '2018-10-24 14:32:21', '2018-10-24 14:32:21');
INSERT INTO `xhx_delivery_mailing_address` VALUES (23170127147175936, 17363094925873152, '王五', '北京', '北京市,酒仙桥', '1001', '111100', 0, '2018-10-24 14:32:21', '2018-10-29 16:55:09');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25338344808517632, 3, '张三', '上海', '上海市,长宁区', '114563', '15600000000', 1, '2018-10-30 14:08:04', '2018-10-30 14:08:04');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25342377271103488, 3, '小红', '天津', '天津市,河东区', '123321', '17600000111', 0, '2018-10-30 14:24:06', '2018-10-30 14:24:06');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25343342745358336, 3, '小红帽', '上海', '上海市,长宁区', '231232', '18911111000', 0, '2018-10-30 14:27:56', '2018-11-13 17:45:29');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25344198249156608, 3, '哈哈', '天津', '天津市,河西区', '123456', '15623232323', 0, '2018-10-30 14:31:20', '2018-10-30 14:31:20');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25350740633587712, 3, '小花', '北京', '北京市,东城区', '963639', '15236974558', 0, '2018-10-30 14:57:20', '2018-10-30 14:57:20');
INSERT INTO `xhx_delivery_mailing_address` VALUES (25353220901703680, 3, '小萌', '北京', '北京市,朝阳区', '111000', '18212121212', 1, '2018-10-30 15:07:11', '2018-10-30 15:07:11');
INSERT INTO `xhx_delivery_mailing_address` VALUES (31197883369918464, 119, '小红花', '上海', '上海市,长宁区', '5678890', '15600000000', 0, '2018-11-15 18:11:47', '2018-11-15 18:11:58');

-- ----------------------------
-- Table structure for xhx_department
-- ----------------------------
DROP TABLE IF EXISTS `xhx_department`;
CREATE TABLE `xhx_department`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parentId` bigint(20) NULL DEFAULT NULL,
  `depPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` int(1) NULL DEFAULT 1,
  `isParent` int(1) NULL DEFAULT 0,
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_department
-- ----------------------------
INSERT INTO `xhx_department` VALUES (1, '股东会', -1, '.1', 1, 1, '2018-09-03 19:59:19', '2018-09-03 19:59:21');
INSERT INTO `xhx_department` VALUES (4, '董事会', 1, '.1.4', 1, 1, '2018-09-03 19:59:22', '2018-09-03 19:59:24');
INSERT INTO `xhx_department` VALUES (5, '总办', 4, '.1.4.5', 1, 1, '2018-09-03 19:59:24', '2018-09-03 19:59:26');
INSERT INTO `xhx_department` VALUES (8, '财务部', 5, '.1.4.5.8', 1, 0, '2018-09-03 19:59:28', '2018-09-03 19:59:29');
INSERT INTO `xhx_department` VALUES (78, '销售部', 5, '.1.4.5.78', 1, 1, '2018-09-03 19:59:31', '2018-09-03 19:59:32');
INSERT INTO `xhx_department` VALUES (91, '技术部', 5, '.1.4.5.91', 1, 0, '2018-09-03 19:59:33', '2018-09-03 19:59:34');
INSERT INTO `xhx_department` VALUES (92, '运维部', 5, '.1.4.5.92', 1, 0, '2018-09-03 19:59:35', '2018-09-03 19:59:38');

-- ----------------------------
-- Table structure for xhx_feed_back
-- ----------------------------
DROP TABLE IF EXISTS `xhx_feed_back`;
CREATE TABLE `xhx_feed_back`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `iphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `context` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '反馈意见' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_feed_back
-- ----------------------------
INSERT INTO `xhx_feed_back` VALUES (1543462962274250, '小明', '111111111', '好好生活', '黄金万两', '1215445@qq.com', '2018-11-29 11:42:42', '2018-11-29 11:42:42');
INSERT INTO `xhx_feed_back` VALUES (1544081466510614, '张三', '15689751236', '金融', '哈哈哈哈', 'mf@hjfh.com', '2018-12-06 15:31:07', '2018-12-06 15:31:07');

-- ----------------------------
-- Table structure for xhx_free_trlal_record
-- ----------------------------
DROP TABLE IF EXISTS `xhx_free_trlal_record`;
CREATE TABLE `xhx_free_trlal_record`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `trialType` int(11) NOT NULL COMMENT '类型：1 试用中 2 已试用',
  `trialDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试用时间',
  `startTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `endTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `createTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '免费试用' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_free_trlal_record
-- ----------------------------
INSERT INTO `xhx_free_trlal_record` VALUES (2, 3, 1, '1', '2018-10-08 15:24:25', '2018-10-08 15:24:25', '2018-10-08 15:24:25', '2018-10-08 15:24:25');
INSERT INTO `xhx_free_trlal_record` VALUES (3, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (4, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (5, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (6, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (7, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (8, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (9, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (10, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (11, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (12, 2, 1, '1', '2018-09-18 17:33:01', '2018-09-18 17:33:02', '2018-09-18 17:33:04', '2018-09-18 17:33:06');
INSERT INTO `xhx_free_trlal_record` VALUES (17320746221178880, 4, 1, '1', '2018-10-08 11:29:18', '2018-10-08 11:29:18', '2018-10-08 11:29:18', '2018-10-08 11:29:18');

-- ----------------------------
-- Table structure for xhx_group_user
-- ----------------------------
DROP TABLE IF EXISTS `xhx_group_user`;
CREATE TABLE `xhx_group_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位公司',
  `mobilePhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `bindMobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户绑定手机',
  `lastLoginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录总次数',
  `profilePicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `enable` int(1) NOT NULL COMMENT '是否启用',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `rechMoney` double NULL DEFAULT NULL COMMENT '充值剩余总金额',
  `giveMoney` double NULL DEFAULT NULL COMMENT '赠送剩余总金额',
  `userCount` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_group_user
-- ----------------------------
INSERT INTO `xhx_group_user` VALUES (1, 'zhangjung', '$2a$10$C8wkAdhuFfh9g/xdduwJkuxR5qJ4NgQpTURKIA1BcVJ2gec5xieZm', '111', '110119120', '111', '111', '2018-09-21 00:00:00', '1', '1', 1, NULL, NULL, 1, 1, 10);
INSERT INTO `xhx_group_user` VALUES (2, 'asd', '$2a$10$8ywyzbz.IR1i.w32CHrc0eGpo4M0p8bCBnjwmLZfS2RSP7Yk2sC06', '111', '11', '111', '111', '2018-09-21 00:00:00', '1', '1', 1, NULL, NULL, 1, 1, 10);
INSERT INTO `xhx_group_user` VALUES (3, 'qwe', '$2a$10$u0xMr8CdR0fv.JIUKH5W8.fUEOi3SWS7YWo6TUMWFgWBx2XBtNStG', '111', '11', '111', '111', '2018-09-21 00:00:00', '1', '1', 1, NULL, NULL, 1, 1, 10);
INSERT INTO `xhx_group_user` VALUES (119, 'xhx_zj', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', '新华信', '18610027352', '18610027351', '106.37.228.170', '2018-12-13 11:05:40', '17', '1', 1, '2018-08-25 00:00:00', '2018-08-31 00:00:00', 1, 1, 10);
INSERT INTO `xhx_group_user` VALUES (1544606049554913, 'AAAAAA', '$2a$10$1MtCAGSjKjS7ciDLsA3PMO9hw.U6R3m5IfVDJuss251bLjJNcj2U2', NULL, NULL, '15612312300', NULL, NULL, '0', NULL, 1, NULL, NULL, NULL, NULL, 0);
INSERT INTO `xhx_group_user` VALUES (10878736757035008, 'xhx_zjg', '$2a$10$1WqGY31b45Ce86PsPuTPeuB22rEVog1XaijqhUY7oLY7j14wcrGVK', '新华信', '10086', '10086', '110.110.110', '2018-09-20 00:00:00', '2', '1', 1, '2018-09-12 17:50:34', '2018-09-12 17:50:39', NULL, NULL, NULL);
INSERT INTO `xhx_group_user` VALUES (10884402997497856, 'yxm', '$2a$10$NSeKIeUyEYh9X3C8s20TNe4oyBUtTQCDPMRyzltU3sKfyfsFJ4itG', '新华信', '10086', '18410055094', '192.168.11.231', '2018-09-20 00:00:00', '1', '1', 0, NULL, NULL, 1, 1, NULL);
INSERT INTO `xhx_group_user` VALUES (10884708271525888, 'werty', '$2a$10$8ywyzbz.IR1i.w32CHrc0eGpo4M0p8bCBnjwmLZfS2RSP7Yk2sC06', '111', '11', '111', '111', '2018-09-21 00:00:00', '1', '1', 0, NULL, NULL, 1, 1, NULL);

-- ----------------------------
-- Table structure for xhx_invoice_aptitude
-- ----------------------------
DROP TABLE IF EXISTS `xhx_invoice_aptitude`;
CREATE TABLE `xhx_invoice_aptitude`  (
  `id` bigint(20) NOT NULL,
  `userId` int(20) NOT NULL,
  `accountName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户名称',
  `taxNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '税务号',
  `context` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发票内容',
  `bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户银行',
  `bankNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行账号',
  `retinfoAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册地址',
  `mailingAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮寄地址',
  `addressee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '增票资质' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_invoice_aptitude
-- ----------------------------
INSERT INTO `xhx_invoice_aptitude` VALUES (1, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '2018-09-21 10:25:46', '2018-09-21 10:25:48');
INSERT INTO `xhx_invoice_aptitude` VALUES (2, 2, '2', '2', '2', '2', '2', '2', '2', '2', '2', '2018-09-21 10:26:05', '2018-09-21 10:26:07');
INSERT INTO `xhx_invoice_aptitude` VALUES (3, 3, '凡士林', '999999', '护肤品', '花旗银行', '621700000000001', '朝阳区', '酒仙桥路', '张三', '15236123612', '2018-11-13 13:23:12', '2018-11-13 13:23:13');
INSERT INTO `xhx_invoice_aptitude` VALUES (4, 4, '4', '4', '4', '4', '4', '4', '4', '4', '4', '2018-09-21 10:26:40', '2018-09-21 10:26:35');
INSERT INTO `xhx_invoice_aptitude` VALUES (5, 5, '5', '5', '5', '5', '5', '5', '5', '5', '5', '2018-09-21 10:27:00', '2018-09-21 10:27:02');
INSERT INTO `xhx_invoice_aptitude` VALUES (6, 6, '6', '6', '6', '6', '6', '6', '6', '6', '6', '2018-09-21 10:27:07', '2018-09-21 10:27:05');
INSERT INTO `xhx_invoice_aptitude` VALUES (7, 7, '7', '7', '7', '7', '7', '7', '7', '7', '7', '2018-09-21 10:27:24', '2018-09-21 10:27:26');
INSERT INTO `xhx_invoice_aptitude` VALUES (8, 8, '8', '8', '8', '8', '8', '8', '8', '8', '8', '2018-09-21 10:27:33', '2018-09-21 10:27:31');
INSERT INTO `xhx_invoice_aptitude` VALUES (9, 9, '9', '9', '9', '9', '9', '9', '9', '9', '9', '2018-09-26 14:00:56', '2018-09-26 14:00:59');
INSERT INTO `xhx_invoice_aptitude` VALUES (11, 11, '11', '11', '11', '11', '11', '11', '11', '11', '11', '2018-09-26 14:01:23', '2018-09-26 14:01:21');
INSERT INTO `xhx_invoice_aptitude` VALUES (31197541416701952, 119, '赵六', '5467587', '办公', '中信银行', '6217002512360000', '朝阳区', '酒仙桥路', '赵六', '18900000000', '2018-11-15 18:10:25', '2018-11-15 18:10:25');

-- ----------------------------
-- Table structure for xhx_invoice_order
-- ----------------------------
DROP TABLE IF EXISTS `xhx_invoice_order`;
CREATE TABLE `xhx_invoice_order`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `invoiceno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票号码',
  `invoiceHeader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票抬头',
  `taxNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税务号',
  `invoiceType` int(1) NULL DEFAULT NULL COMMENT '发票类型:\r\n0 普通发票\r\n1 增值税发票',
  `invoiceNature` int(1) NULL DEFAULT NULL COMMENT '发票性值\r\n0 纸质\r\n1 电子',
  `orderAmount` double(20, 2) NULL DEFAULT NULL COMMENT '发票金额',
  `orderStatus` int(1) NULL DEFAULT NULL COMMENT '发票状态\r\n0、未开发票 1、已开发票',
  `context` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票内容',
  `deliveryType` int(1) NULL DEFAULT NULL COMMENT '配送方式：0 快递1 下载',
  `mailingAdd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮寄地址',
  `addressee` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `orderTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `createTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注：开票特殊要求',
  `expressRemark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递备注 ：快递类型+快递单号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票订单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_invoice_order
-- ----------------------------
INSERT INTO `xhx_invoice_order` VALUES (2, 2, 'qwe', '张军', '2', 2, 1, 2.00, 1, '哈哈哈哈哈哈', 1, '2', '2', '2018-10-26 17:31:35', '2018-09-12 10:21:26', '2018-09-12 10:21:29', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (3, 3, '3', '3', '3', 3, 1, 3.00, 0, '3', 0, '3', '3', '2018-09-25 16:04:51', '2018-09-20 15:26:58', '2018-09-20 15:27:01', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (4, 4, '4', '4', '4', 4, 0, 4.00, 0, '4', 0, '4', '4', '2018-10-26 17:31:39', '2018-09-20 15:28:35', '2018-09-20 15:28:38', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (6, 6, '6', '5', '5', 5, 1, 0.00, 1, '5', 1, '1', '5', '2018-10-26 17:31:41', '2018-09-20 15:29:01', '2018-09-20 15:29:03', '', NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (7, 7, '7', '7', '7', 7, 0, 7.00, 0, '7', 1, '7', '7', '2018-10-26 17:31:44', '2018-09-20 15:31:57', '2018-09-20 15:32:00', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (8, 8, '8', '8', '8', 8, 0, 8.00, 1, '8', 0, '8', '8', '2018-10-26 17:31:47', '2018-09-20 15:32:16', '2018-09-20 15:32:18', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (9, 9, '9', '9', '9', 9, 1, 9.00, 1, '9', 1, '9', '9', '2018-10-26 17:31:51', '2018-09-20 15:33:02', '2018-09-20 15:33:06', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (10, 3, '10', '10', '10', 10, 1, 10.00, 0, '1000', 1, '10', '10', '2018-09-26 18:41:10', '2018-09-20 15:33:29', '2018-09-20 15:33:32', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (11, 119, '11', '11', '11', 11, 1, 11.00, 1, '11', 1, '11', '11', '2018-10-26 16:26:20', '2018-09-20 15:33:55', '2018-09-20 15:33:58', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (10874264597172224, 3, '1', '漳卅三年', '125215', 2, 2, 0.00, 1, '购买办公用品', 1, '1', '1', '2018-09-21 10:06:09', '2018-09-21 10:06:01', '2018-09-21 10:06:04', '1', '1', '1');
INSERT INTO `xhx_invoice_order` VALUES (14194969971658752, 3, '1', '漳卅三年', '125215', 2, 2, 515.00, 0, '购买办公用品', NULL, NULL, NULL, '2018-10-30 14:48:50', '2018-09-29 20:08:17', '2018-09-29 20:08:17', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27868106462466048, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:40:26', '2018-11-06 13:40:26', '2018-11-06 13:40:26', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27868138959933440, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:40:34', '2018-11-06 13:40:34', '2018-11-06 13:40:34', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27869289394606080, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:45:08', '2018-11-06 13:45:08', '2018-11-06 13:45:08', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27869941382385664, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:47:43', '2018-11-06 13:47:44', '2018-11-06 13:47:44', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27870238951477248, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:48:54', '2018-11-06 13:48:55', '2018-11-06 13:48:55', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27870931502370816, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:51:39', '2018-11-06 13:51:40', '2018-11-06 13:51:40', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871176353255424, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:52:38', '2018-11-06 13:52:38', '2018-11-06 13:52:38', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871210381643776, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:52:46', '2018-11-06 13:52:46', '2018-11-06 13:52:46', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871432809779200, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:53:39', '2018-11-06 13:53:39', '2018-11-06 13:53:39', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871567346274304, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:54:11', '2018-11-06 13:54:11', '2018-11-06 13:54:11', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871602356129792, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:54:19', '2018-11-06 13:54:20', '2018-11-06 13:54:20', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871852143710208, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:55:19', '2018-11-06 13:55:19', '2018-11-06 13:55:19', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27871998805938176, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:55:54', '2018-11-06 13:55:54', '2018-11-06 13:55:54', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27872118469431296, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:56:22', '2018-11-06 13:56:23', '2018-11-06 13:56:23', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27872146906812416, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:56:29', '2018-11-06 13:56:30', '2018-11-06 13:56:30', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27872473282383872, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:57:47', '2018-11-06 13:57:47', '2018-11-06 13:57:47', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27872498364321792, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 13:57:53', '2018-11-06 13:57:53', '2018-11-06 13:57:53', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27873087227826176, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:00:13', '2018-11-06 14:00:14', '2018-11-06 14:00:14', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27873151195156480, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:00:29', '2018-11-06 14:00:29', '2018-11-06 14:00:29', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27873155821473792, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:00:30', '2018-11-06 14:00:30', '2018-11-06 14:00:30', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27876322298695680, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:13:05', '2018-11-06 14:13:05', '2018-11-06 14:13:05', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27876511184982016, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:13:50', '2018-11-06 14:13:50', '2018-11-06 14:13:50', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27876584493027328, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:14:07', '2018-11-06 14:14:08', '2018-11-06 14:14:08', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27877784722477056, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:18:53', '2018-11-06 14:18:54', '2018-11-06 14:18:54', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27877816938926080, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:19:01', '2018-11-06 14:19:02', '2018-11-06 14:19:02', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27877840338948096, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:19:07', '2018-11-06 14:19:07', '2018-11-06 14:19:07', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27882749515927552, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:38:37', '2018-11-06 14:38:38', '2018-11-06 14:38:38', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883028827213824, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:39:44', '2018-11-06 14:39:44', '2018-11-06 14:39:44', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883438925287424, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:41:21', '2018-11-06 14:41:22', '2018-11-06 14:41:22', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883672443162624, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:42:17', '2018-11-06 14:42:18', '2018-11-06 14:42:18', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883910029512704, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:14', '2018-11-06 14:43:14', '2018-11-06 14:43:14', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883924323700736, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:17', '2018-11-06 14:43:18', '2018-11-06 14:43:18', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883971111161856, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:28', '2018-11-06 14:43:29', '2018-11-06 14:43:29', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883972948267008, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:29', '2018-11-06 14:43:29', '2018-11-06 14:43:29', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883974248501248, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:29', '2018-11-06 14:43:30', '2018-11-06 14:43:30', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27883975238356992, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:43:29', '2018-11-06 14:43:30', '2018-11-06 14:43:30', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27884211721605120, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:44:26', '2018-11-06 14:44:26', '2018-11-06 14:44:26', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27884216779935744, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:44:27', '2018-11-06 14:44:27', '2018-11-06 14:44:27', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27884279606415360, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:44:42', '2018-11-06 14:44:42', '2018-11-06 14:44:42', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27884853601112064, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:46:59', '2018-11-06 14:46:59', '2018-11-06 14:46:59', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885427008606208, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:49:15', '2018-11-06 14:49:16', '2018-11-06 14:49:16', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885557082361856, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:49:46', '2018-11-06 14:49:47', '2018-11-06 14:49:47', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885588518670336, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:49:54', '2018-11-06 14:49:54', '2018-11-06 14:49:54', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885607749554176, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:49:58', '2018-11-06 14:49:59', '2018-11-06 14:49:59', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885611302129664, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:49:59', '2018-11-06 14:50:00', '2018-11-06 14:50:00', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885794173784064, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:50:43', '2018-11-06 14:50:43', '2018-11-06 14:50:43', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885798007377920, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:50:44', '2018-11-06 14:50:44', '2018-11-06 14:50:44', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27885827656912896, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:50:51', '2018-11-06 14:50:51', '2018-11-06 14:50:51', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27886405040607232, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:53:09', '2018-11-06 14:53:09', '2018-11-06 14:53:09', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27886456374693888, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:53:21', '2018-11-06 14:53:21', '2018-11-06 14:53:21', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27886592274337792, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:53:53', '2018-11-06 14:53:54', '2018-11-06 14:53:54', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27886695064145920, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 14:54:18', '2018-11-06 14:54:18', '2018-11-06 14:54:18', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27888915167973376, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:03:07', '2018-11-06 15:03:08', '2018-11-06 15:03:08', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27888919571992576, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:03:08', '2018-11-06 15:03:09', '2018-11-06 15:03:09', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27888921430069248, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:03:09', '2018-11-06 15:03:09', '2018-11-06 15:03:09', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27889573858250752, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:05:44', '2018-11-06 15:05:45', '2018-11-06 15:05:45', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27890883219296256, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:10:56', '2018-11-06 15:10:57', '2018-11-06 15:10:57', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (27890958372835328, 3, NULL, '', '', 0, 0, NULL, NULL, '请选择', NULL, NULL, NULL, '2018-11-06 15:11:14', '2018-11-06 15:11:15', '2018-11-06 15:11:15', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (28271264376426496, 3, NULL, '张三', '23076543', 0, 0, 2798765.00, NULL, '信息服务费', NULL, NULL, NULL, '2018-11-07 16:22:26', '2018-11-07 16:22:27', '2018-11-07 16:22:27', NULL, NULL, NULL);
INSERT INTO `xhx_invoice_order` VALUES (30400103277793280, 3, NULL, '张三', '6876876868', 0, 0, 5679889.00, NULL, '咨询费', NULL, NULL, NULL, '2018-11-13 13:21:41', '2018-11-13 13:21:41', '2018-11-13 13:21:41', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xhx_menu
-- ----------------------------
DROP TABLE IF EXISTS `xhx_menu`;
CREATE TABLE `xhx_menu`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `iconCls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL,
  `parentId` bigint(20) NULL DEFAULT NULL,
  `enabled` int(1) NULL DEFAULT 1,
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parentId`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `xhx_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_menu
-- ----------------------------
INSERT INTO `xhx_menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (2, '/', '/home', 'Home', '发票管理', 'fa fa-user-circle-o', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (3, '/', '/home', 'Home', '订单管理', 'fa fa-address-card-o', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (4, '/', '/home', 'Home', '充值消费管理', 'fa fa-money', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (5, '/', '/home', 'Home', '用户管理', 'fa fa-bar-chart', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (6, '/', '/home', 'Home', '客户管理', 'fa fa-bar-chart', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (7, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, 1, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (8, '/invoice/**', '/emp', 'EmpBasic', '发票订单', NULL, NULL, 1, 2, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (9, '/invoice/adv/**', '/emp/adv', 'EmpAdv', '发票资质', NULL, NULL, 1, 2, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (10, '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', NULL, NULL, 1, 3, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (11, '/personnel/ec/**', '/per/ec', 'PerEc', '订单查询', NULL, NULL, 1, 3, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (15, '/salary/sob/**', '/sal/sob', 'SalSob', '充值消费管理', NULL, NULL, 1, 4, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (17, '/salary/table/**', '/sal/table', 'SalTable', '角色管理列表', NULL, NULL, 1, 7, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (20, '/statistics/all/**', '/sta/all', 'StaAll', '免费试用记录', NULL, NULL, 1, 5, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (21, '/statistics/score/**', '/sta/score', 'StaScore', '风险监控管理', NULL, NULL, 1, 5, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (22, '/statistics/personnel/**', '/sta/pers', 'StaPers', '前台用户管理', NULL, NULL, 1, 5, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (23, '/statistics/recored/**', '/sta/record', 'StaRecord', '后台用户管理', NULL, NULL, 1, 5, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (24, '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', NULL, NULL, 1, 6, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (25, '/system/cfg/**', '/sys/cfg', 'SysCfg', '客户查询', NULL, NULL, 1, 6, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (26, '/system/log/**', '/sys/log', 'SysLog', '权限管理列表', NULL, NULL, 1, 7, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (27, '/system/hr/**', '/sys/hr', 'SysHr', '角色分配菜单', NULL, NULL, 1, 7, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (28, '/system/data/**', '/sys/data', 'SysData', '风险监控菜单', NULL, NULL, 1, 7, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (29, '/system/init/**', '/sys/init', 'SysInit', '角色', NULL, NULL, 1, 7, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (30, '/login/*', '/login', 'login', '前台登陆', NULL, NULL, 2, 1, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (31, '/', '/index', 'index', '用户中心', NULL, NULL, 2, 30, 1, NULL, NULL);
INSERT INTO `xhx_menu` VALUES (32, '/', '/index', 'index', '客户中心', NULL, NULL, 2, 30, 1, NULL, NULL);

-- ----------------------------
-- Table structure for xhx_merchandise_invoice
-- ----------------------------
DROP TABLE IF EXISTS `xhx_merchandise_invoice`;
CREATE TABLE `xhx_merchandise_invoice`  (
  `id` bigint(20) NOT NULL,
  `invoiceId` bigint(20) NOT NULL COMMENT '发票订单ID',
  `merchandiseId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务订单ID',
  `createTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for xhx_merchandise_order
-- ----------------------------
DROP TABLE IF EXISTS `xhx_merchandise_order`;
CREATE TABLE `xhx_merchandise_order`  (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `userId` bigint(20) NULL DEFAULT NULL,
  `orderName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `orderType` int(2) NULL DEFAULT NULL COMMENT '订单类型:1 企业信用报告 2 行业研究报告 3 信用检测\r\n  4 数据导出',
  `orderAmount` double(5, 0) NULL DEFAULT NULL COMMENT '订单金额',
  `orderNo` bigint(50) NULL DEFAULT NULL COMMENT '订单号',
  `invoiceStatus` int(2) NULL DEFAULT NULL COMMENT '0、未开发票 1、已开发票\r\n',
  `orderTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订购时间',
  `createTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00',
  `orderContent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单内容',
  `orderTimeLong` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderStatus` int(2) NULL DEFAULT NULL COMMENT '订单状态：1 未支付 2 已完成 3 关闭',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务订单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_merchandise_order
-- ----------------------------
INSERT INTO `xhx_merchandise_order` VALUES (1, 1, 'asd', 1, 1, 122, 1, '2018-09-27 14:59:47', '2018-09-17 17:53:11', '2018-09-17 17:53:13', '1', NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (2, 2, 'qwe', 1, 1, 43, 1, '2018-09-21 10:26:11', '2018-09-21 10:26:14', '2018-09-21 10:26:19', '2', NULL, 2);
INSERT INTO `xhx_merchandise_order` VALUES (3, 3, 'zcx', 4, 1, 23, 1, '2018-09-27 15:00:09', '2018-09-21 10:26:38', '2018-09-21 10:26:40', '3', NULL, 3);
INSERT INTO `xhx_merchandise_order` VALUES (4, 4, 'gfhg', 1, 1, 25, 1, '2018-10-08 14:35:37', '2018-09-21 10:27:04', '2018-09-21 10:27:07', '4', NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (5, 5, 'trwq', 2, 1, 56, 1, '2018-10-08 14:34:59', '2018-09-21 10:27:31', '2018-09-21 10:27:33', '5', NULL, 3);
INSERT INTO `xhx_merchandise_order` VALUES (6, 6, 'koioiu', 3, 1, 79, 1, '2018-10-08 14:35:14', '2018-09-21 10:27:48', '2018-09-21 10:27:46', '6', NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (7, 7, 'sadvcb', 2, 1, 6312, 1, '2018-10-08 14:34:55', '2018-09-21 10:28:23', '2018-09-21 10:28:26', '7', NULL, 2);
INSERT INTO `xhx_merchandise_order` VALUES (8, 8, 'mjds', 2, 1, 654, 1, '2018-10-08 14:35:33', '2018-09-21 10:28:37', '2018-09-21 10:28:35', '8', NULL, 3);
INSERT INTO `xhx_merchandise_order` VALUES (9, 9, 'cvcxr', 2, 1, 545, 1, '2018-10-08 14:35:28', '2018-09-21 10:29:10', '2018-09-21 10:29:12', '9', NULL, 3);
INSERT INTO `xhx_merchandise_order` VALUES (10, 10, 'khdfsa', 3, 1, 994, 1, '2018-10-08 14:34:49', '2018-09-21 10:29:24', '2018-09-21 10:29:22', '10', NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (11, 11, 'vcxzd', 3, 1, 5624, 1, '2018-10-08 14:35:21', '2018-09-21 10:29:57', '2018-09-21 10:30:00', '11', NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (12, 12, 'ghge', 1, 1, 345, 1, '2018-10-08 14:35:25', '2018-09-21 10:30:10', '2018-09-21 10:30:08', '12', NULL, 2);
INSERT INTO `xhx_merchandise_order` VALUES (1544517061196913, 3, '商丘铭鑫汽车保险代理有限公司', 3, 35, 2018121139706531, NULL, '2018-12-11 16:31:01', '2018-12-11 16:31:01', '0000-00-00 00:00:00', NULL, '1', 1);
INSERT INTO `xhx_merchandise_order` VALUES (1544520275934776, 3, '大博医疗科技股份有限公司', 3, 35, 2018121132781085, NULL, '2018-12-11 17:24:36', '2018-12-11 17:24:36', '0000-00-00 00:00:00', NULL, '1', 1);
INSERT INTO `xhx_merchandise_order` VALUES (23606637172494336, 2, '中国传统文化促进会', 1, 35, 2018102527023882, NULL, '2018-10-25 19:26:54', '2018-10-25 19:26:53', '2018-10-25 19:26:53', NULL, NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (23828964267134976, 1, '中国新兴工程建筑房地产开发总公司', 1, 35, 2018102618078388, NULL, '2018-10-26 10:10:20', '2018-10-26 10:10:20', '2018-10-26 10:10:20', NULL, NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (23831829199065088, 3, '中国新兴工程建筑房地产开发总公司', 1, 35, 2018102616912716, NULL, '2018-10-30 14:52:25', '2018-10-26 10:21:43', '2018-10-26 10:21:43', NULL, NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (23833996005871616, 1, '中国新兴工程建筑房地产开发总公司', 1, 35, 2018102615733002, NULL, '2018-10-26 10:30:20', '2018-10-26 10:30:19', '2018-10-26 10:30:19', NULL, NULL, 1);
INSERT INTO `xhx_merchandise_order` VALUES (25339502067322880, 1, '中国新兴工程建筑房地产开发总公司', 1, 35, 2018103011015810, NULL, '2018-10-30 14:12:40', '2018-10-30 14:12:40', '0000-00-00 00:00:00', NULL, '1', 1);
INSERT INTO `xhx_merchandise_order` VALUES (25340389846290432, 1, '中国新兴工程建筑房地产开发总公司', 1, 35, 2018103018334618, NULL, '2018-10-30 14:16:12', '2018-10-30 14:16:12', '0000-00-00 00:00:00', NULL, '1', 1);

-- ----------------------------
-- Table structure for xhx_new_risk_tip
-- ----------------------------
DROP TABLE IF EXISTS `xhx_new_risk_tip`;
CREATE TABLE `xhx_new_risk_tip`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `userId` int(20) NOT NULL,
  `riskTipDate` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `riskTipItem` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险提示项目',
  `createTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_new_risk_tip
-- ----------------------------
INSERT INTO `xhx_new_risk_tip` VALUES (1, '新华信1', 3, '2018-12-12 11:43:06', '被执行人', '2018-12-12 11:43:06', '2018-12-12 11:43:06');
INSERT INTO `xhx_new_risk_tip` VALUES (2, '新华信2', 3, '2018-10-29 14:03:15', '经营异常', '2018-10-29 14:03:15', '2018-10-29 14:03:15');
INSERT INTO `xhx_new_risk_tip` VALUES (3, '商丘铭鑫汽车保险代理有限公司', 3, '2018-12-13 18:30:37', '负面信息', '2018-12-13 18:30:37', '2018-12-13 18:30:37');
INSERT INTO `xhx_new_risk_tip` VALUES (4, '大博医疗科技股份有限公司', 3, '2018-12-17 10:26:57', '股东股份', '2018-12-17 10:26:57', '2018-12-17 10:26:57');

-- ----------------------------
-- Table structure for xhx_re_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `xhx_re_role_menu`;
CREATE TABLE `xhx_re_role_menu`  (
  `id` bigint(20) NOT NULL,
  `mId` bigint(20) NOT NULL COMMENT '模块ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_danish_ci COMMENT = '角色权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_re_role_menu
-- ----------------------------
INSERT INTO `xhx_re_role_menu` VALUES (161, 7, 3, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (162, 8, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (163, 9, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (164, 10, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (165, 11, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (169, 15, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (171, 17, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (174, 20, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (175, 21, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (176, 22, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (177, 23, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (178, 25, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (179, 26, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (180, 27, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (181, 28, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (182, 24, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (247, 7, 4, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (248, 8, 4, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (249, 11, 4, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (250, 7, 2, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (251, 8, 2, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (252, 9, 2, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (253, 10, 2, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (256, 7, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (257, 8, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (258, 9, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (259, 10, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (260, 11, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (264, 15, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (266, 17, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (269, 20, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (270, 21, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (271, 22, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (272, 23, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (273, 24, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (274, 25, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (275, 26, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (276, 27, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (277, 28, 1, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (278, 30, 15, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (279, 30, 16, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (280, 30, 17, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (281, 2, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (282, 3, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (283, 4, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (284, 5, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (285, 6, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (286, 7, 6, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (287, 30, 18, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (288, 32, 18, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (289, 30, 19, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (290, 32, 19, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (291, 30, 20, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (292, 32, 20, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (293, 31, 15, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (294, 31, 16, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (295, 31, 17, NULL, NULL);
INSERT INTO `xhx_re_role_menu` VALUES (18393291078897664, 5, 3, '2018-10-11 10:10:54', '2018-10-11 10:10:54');
INSERT INTO `xhx_re_role_menu` VALUES (18393291095674880, 6, 3, '2018-10-11 10:10:54', '2018-10-11 10:10:54');

-- ----------------------------
-- Table structure for xhx_re_user_role
-- ----------------------------
DROP TABLE IF EXISTS `xhx_re_user_role`;
CREATE TABLE `xhx_re_user_role`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `roleId` bigint(20) NOT NULL COMMENT '角色ID',
  `startTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '角色开始时间',
  `endTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '角色结束时间',
  `createTime` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_danish_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_re_user_role
-- ----------------------------
INSERT INTO `xhx_re_user_role` VALUES (1, 1, 6, '2018-09-04 13:53:59', '2018-09-03 10:58:35', NULL, NULL);
INSERT INTO `xhx_re_user_role` VALUES (2, 2, 6, '2018-09-13 17:48:01', '2018-09-03 10:59:05', '2018-09-13 17:48:01', '2018-09-13 17:48:01');
INSERT INTO `xhx_re_user_role` VALUES (3, 3, 17, '2018-11-14 15:54:47', '2018-09-03 10:59:24', '2018-11-14 15:54:47', '2018-11-14 15:54:47');
INSERT INTO `xhx_re_user_role` VALUES (4, 119, 17, '2018-11-15 10:34:57', '2018-09-04 17:34:21', '2018-11-15 10:34:57', '2018-11-15 10:34:57');
INSERT INTO `xhx_re_user_role` VALUES (5, 8717380356280320, 18, '2018-11-15 10:35:06', '2018-09-16 12:58:58', '2018-11-15 10:35:06', '2018-11-15 10:35:06');
INSERT INTO `xhx_re_user_role` VALUES (6, 8713172336906240, 16, '2018-09-16 13:01:23', '2018-09-30 13:00:33', '2018-09-16 13:01:23', '2018-09-16 13:01:23');
INSERT INTO `xhx_re_user_role` VALUES (7, 8712881088630784, 17, '2018-09-16 13:01:52', '2019-09-16 13:01:57', NULL, NULL);
INSERT INTO `xhx_re_user_role` VALUES (154476494541730, 1544764945413144, 15, '2018-12-14 13:22:25', '2018-12-14 13:22:25', '2018-12-14 13:22:25', '2018-12-14 13:22:25');
INSERT INTO `xhx_re_user_role` VALUES (154476546372898, 1544765463724275, 15, '2018-12-14 13:31:04', '2018-12-14 13:31:04', '2018-12-14 13:31:04', '2018-12-14 13:31:04');
INSERT INTO `xhx_re_user_role` VALUES (154501764921739, 1545017649214590, 15, '2018-12-17 11:34:09', '2018-12-17 11:34:09', '2018-12-17 11:34:09', '2018-12-17 11:34:09');
INSERT INTO `xhx_re_user_role` VALUES (1544606075836252, 1544606049554913, 18, '2018-12-12 17:14:36', '2018-12-12 17:14:36', '2018-12-12 17:14:36', '2018-12-12 17:14:36');
INSERT INTO `xhx_re_user_role` VALUES (1544694266427128, 1544694266417824, 15, '2018-12-13 17:44:26', '2018-12-13 17:44:26', '2018-12-13 17:44:26', '2018-12-13 17:44:26');
INSERT INTO `xhx_re_user_role` VALUES (1544694746228863, 1544694746223166, 15, '2018-12-13 17:52:26', '2018-12-13 17:52:26', '2018-12-13 17:52:26', '2018-12-13 17:52:26');
INSERT INTO `xhx_re_user_role` VALUES (1544694854465293, 1544694854462236, 15, '2018-12-13 17:54:14', '2018-12-13 17:54:14', '2018-12-13 17:54:14', '2018-12-13 17:54:14');
INSERT INTO `xhx_re_user_role` VALUES (1544696052296651, 1544696052293446, 15, '2018-12-13 18:14:12', '2018-12-13 18:14:12', '2018-12-13 18:14:12', '2018-12-13 18:14:12');
INSERT INTO `xhx_re_user_role` VALUES (1544758897866117, 1544758897862459, 15, '2018-12-14 11:41:38', '2018-12-14 11:41:38', '2018-12-14 11:41:38', '2018-12-14 11:41:38');
INSERT INTO `xhx_re_user_role` VALUES (1544765707389728, 1544765707385959, 15, '2018-12-14 13:35:07', '2018-12-14 13:35:07', '2018-12-14 13:35:07', '2018-12-14 13:35:07');
INSERT INTO `xhx_re_user_role` VALUES (1544766116092281, 1544766116089167, 15, '2018-12-14 13:41:56', '2018-12-14 13:41:56', '2018-12-14 13:41:56', '2018-12-14 13:41:56');
INSERT INTO `xhx_re_user_role` VALUES (1544767532799543, 1544767532796874, 15, '2018-12-14 14:05:33', '2018-12-14 14:05:33', '2018-12-14 14:05:33', '2018-12-14 14:05:33');
INSERT INTO `xhx_re_user_role` VALUES (1544781041197530, 1544781041194476, 15, '2018-12-14 17:50:41', '2018-12-14 17:50:41', '2018-12-14 17:50:41', '2018-12-14 17:50:41');
INSERT INTO `xhx_re_user_role` VALUES (1544784127683713, 1544784127678338, 15, '2018-12-14 18:42:08', '2018-12-14 18:42:08', '2018-12-14 18:42:08', '2018-12-14 18:42:08');
INSERT INTO `xhx_re_user_role` VALUES (1545017553510639, 1545017553506644, 15, '2018-12-17 11:32:34', '2018-12-17 11:32:34', '2018-12-17 11:32:34', '2018-12-17 11:32:34');
INSERT INTO `xhx_re_user_role` VALUES (1545017738752941, 1545017738749355, 15, '2018-12-17 11:35:39', '2018-12-17 11:35:39', '2018-12-17 11:35:39', '2018-12-17 11:35:39');
INSERT INTO `xhx_re_user_role` VALUES (1545017908131899, 1545017908127136, 15, '2018-12-17 11:38:28', '2018-12-17 11:38:28', '2018-12-17 11:38:28', '2018-12-17 11:38:28');
INSERT INTO `xhx_re_user_role` VALUES (1545018013634478, 1545018013631610, 15, '2018-12-17 11:40:14', '2018-12-17 11:40:14', '2018-12-17 11:40:14', '2018-12-17 11:40:14');
INSERT INTO `xhx_re_user_role` VALUES (1545018117056185, 1545018117053855, 15, '2018-12-17 11:41:57', '2018-12-17 11:41:57', '2018-12-17 11:41:57', '2018-12-17 11:41:57');
INSERT INTO `xhx_re_user_role` VALUES (1545018303286377, 1545018303282784, 15, '2018-12-17 11:45:03', '2018-12-17 11:45:03', '2018-12-17 11:45:03', '2018-12-17 11:45:03');
INSERT INTO `xhx_re_user_role` VALUES (11153119564140544, 11153119509614592, 15, '2018-09-21 10:41:03', '2018-09-21 10:41:03', '2018-09-21 10:41:03', '2018-09-21 10:41:03');
INSERT INTO `xhx_re_user_role` VALUES (11221783269216256, 11221783223078912, 15, '2018-09-21 15:13:54', '2018-09-21 15:13:54', '2018-09-21 15:13:54', '2018-09-21 15:13:54');
INSERT INTO `xhx_re_user_role` VALUES (13465225961541632, 13465225919598592, 15, '2018-09-27 19:48:32', '2018-09-27 19:48:32', '2018-09-27 19:48:32', '2018-09-27 19:48:32');
INSERT INTO `xhx_re_user_role` VALUES (13476269438144512, 13476269396201472, 15, '2018-09-27 20:32:25', '2018-09-27 20:32:25', '2018-09-27 20:32:25', '2018-09-27 20:32:25');
INSERT INTO `xhx_re_user_role` VALUES (13480570537512960, 13480570495569920, 15, '2018-09-27 20:49:30', '2018-09-27 20:49:30', '2018-09-27 20:49:30', '2018-09-27 20:49:30');
INSERT INTO `xhx_re_user_role` VALUES (13485993185382400, 13485993156022272, 15, '2018-09-27 21:11:03', '2018-09-27 21:11:03', '2018-09-27 21:11:03', '2018-09-27 21:11:03');
INSERT INTO `xhx_re_user_role` VALUES (13680568109764608, 13680568088793088, 15, '2018-09-28 10:04:14', '2018-09-28 10:04:14', '2018-09-28 10:04:14', '2018-09-28 10:04:14');
INSERT INTO `xhx_re_user_role` VALUES (13685457237970944, 13685457216999424, 15, '2018-09-28 10:23:39', '2018-09-28 10:23:39', '2018-09-28 10:23:39', '2018-09-28 10:23:39');
INSERT INTO `xhx_re_user_role` VALUES (13687041594363904, 13687041304956928, 18, '2018-11-15 10:35:25', '2018-09-28 10:29:57', '2018-11-15 10:35:25', '2018-11-15 10:35:25');
INSERT INTO `xhx_re_user_role` VALUES (13725953805127680, 13725953771573248, 15, '2018-09-28 13:04:34', '2018-09-28 13:04:34', '2018-09-28 13:04:34', '2018-09-28 13:04:34');
INSERT INTO `xhx_re_user_role` VALUES (13726325084917760, 13726325063946240, 15, '2018-09-28 13:06:03', '2018-09-28 13:06:03', '2018-09-28 13:06:03', '2018-09-28 13:06:03');
INSERT INTO `xhx_re_user_role` VALUES (13729257939406848, 13729257918435328, 15, '2018-09-28 13:17:42', '2018-09-28 13:17:42', '2018-09-28 13:17:42', '2018-09-28 13:17:42');
INSERT INTO `xhx_re_user_role` VALUES (13729839152500736, 13729839118946304, 15, '2018-09-28 13:20:01', '2018-09-28 13:20:01', '2018-09-28 13:20:01', '2018-09-28 13:20:01');
INSERT INTO `xhx_re_user_role` VALUES (13730229231161344, 13730229197606912, 16, '2018-11-15 10:35:16', '2018-09-28 13:21:34', '2018-11-15 10:35:16', '2018-11-15 10:35:16');
INSERT INTO `xhx_re_user_role` VALUES (13731600424636416, 13731600403664896, 15, '2018-09-28 13:27:01', '2018-09-28 13:27:01', '2018-09-28 13:27:01', '2018-09-28 13:27:01');
INSERT INTO `xhx_re_user_role` VALUES (13731982022414336, 13731982001442816, 15, '2018-09-28 13:28:32', '2018-09-28 13:28:32', '2018-09-28 13:28:32', '2018-09-28 13:28:32');
INSERT INTO `xhx_re_user_role` VALUES (13732244611010560, 13732244594233344, 15, '2018-09-28 13:29:34', '2018-09-28 13:29:34', '2018-09-28 13:29:34', '2018-09-28 13:29:34');
INSERT INTO `xhx_re_user_role` VALUES (13733884378353664, 13733884361576448, 15, '2018-09-28 13:36:05', '2018-09-28 13:36:05', '2018-09-28 13:36:05', '2018-09-28 13:36:05');
INSERT INTO `xhx_re_user_role` VALUES (13735590080155648, 13735590063378432, 15, '2018-09-28 13:42:52', '2018-09-28 13:42:52', '2018-09-28 13:42:52', '2018-09-28 13:42:52');
INSERT INTO `xhx_re_user_role` VALUES (13735876085551104, 13735876060385280, 17, '2018-11-15 10:35:22', '2018-09-28 13:44:00', '2018-11-15 10:35:22', '2018-11-15 10:35:22');
INSERT INTO `xhx_re_user_role` VALUES (13737310977921024, 13737310952755200, 15, '2018-09-28 13:49:42', '2018-09-28 13:49:42', '2018-09-28 13:49:42', '2018-09-28 13:49:42');
INSERT INTO `xhx_re_user_role` VALUES (13738439698681856, 13738439577047040, 15, '2018-09-28 13:54:11', '2018-09-28 13:54:11', '2018-09-28 13:54:11', '2018-09-28 13:54:11');
INSERT INTO `xhx_re_user_role` VALUES (13739244916969472, 13739243637706752, 15, '2018-09-28 13:57:23', '2018-09-28 13:57:23', '2018-09-28 13:57:23', '2018-09-28 13:57:23');
INSERT INTO `xhx_re_user_role` VALUES (13741565713453056, 13741565696675840, 15, '2018-09-28 14:06:37', '2018-09-28 14:06:37', '2018-09-28 14:06:37', '2018-09-28 14:06:37');
INSERT INTO `xhx_re_user_role` VALUES (13741790880468992, 13741789588623360, 15, '2018-09-28 14:07:30', '2018-09-28 14:07:30', '2018-09-28 14:07:30', '2018-09-28 14:07:30');
INSERT INTO `xhx_re_user_role` VALUES (13794888726482944, 13794888600653824, 15, '2018-09-28 17:38:30', '2018-09-28 17:38:30', '2018-09-28 17:38:30', '2018-09-28 17:38:30');
INSERT INTO `xhx_re_user_role` VALUES (13797293782339584, 13797293761368064, 15, '2018-09-28 17:48:03', '2018-09-28 17:48:03', '2018-09-28 17:48:03', '2018-09-28 17:48:03');
INSERT INTO `xhx_re_user_role` VALUES (13797743210401792, 13797743193624576, 15, '2018-09-28 17:49:50', '2018-09-28 17:49:50', '2018-09-28 17:49:50', '2018-09-28 17:49:50');
INSERT INTO `xhx_re_user_role` VALUES (14491182616940544, 14491182579191808, 15, '2018-09-30 15:45:19', '2018-09-30 15:45:19', '2018-09-30 15:45:19', '2018-09-30 15:45:19');
INSERT INTO `xhx_re_user_role` VALUES (17310163845386240, 17310141770764288, 18, '2018-10-08 10:26:58', '2018-10-08 10:26:58', '2018-10-08 10:26:57', '2018-10-08 10:26:58');
INSERT INTO `xhx_re_user_role` VALUES (17316460003201024, 17316393859026944, 18, '2018-10-08 10:51:58', '2018-10-08 10:51:58', '2018-10-08 10:51:58', '2018-10-08 10:51:58');
INSERT INTO `xhx_re_user_role` VALUES (17363095026536448, 17363094925873152, 15, '2018-10-08 13:57:16', '2018-10-08 13:57:16', '2018-10-08 13:57:16', '2018-10-08 13:57:16');
INSERT INTO `xhx_re_user_role` VALUES (17363401793736704, 17363401760182272, 15, '2018-10-08 13:58:30', '2018-10-08 13:58:30', '2018-10-08 13:58:30', '2018-10-08 13:58:30');
INSERT INTO `xhx_re_user_role` VALUES (17363848713605120, 17363848696827904, 15, '2018-10-08 14:00:16', '2018-10-08 14:00:16', '2018-10-08 14:00:16', '2018-10-08 14:00:16');
INSERT INTO `xhx_re_user_role` VALUES (17366790237720576, 17366790220943360, 15, '2018-10-08 14:11:57', '2018-10-08 14:11:57', '2018-10-08 14:11:57', '2018-10-08 14:11:57');
INSERT INTO `xhx_re_user_role` VALUES (17373537736200192, 17373537711034368, 15, '2018-10-08 14:38:46', '2018-10-08 14:38:46', '2018-10-08 14:38:46', '2018-10-08 14:38:46');
INSERT INTO `xhx_re_user_role` VALUES (17379023487373312, 17379023458013184, 15, '2018-10-08 15:00:34', '2018-10-08 15:00:34', '2018-10-08 15:00:34', '2018-10-08 15:00:34');
INSERT INTO `xhx_re_user_role` VALUES (18543677924315136, 4, 1, '2018-10-26 15:58:18', '2018-10-11 20:08:29', '2018-10-26 15:58:18', '2018-10-26 15:58:18');
INSERT INTO `xhx_re_user_role` VALUES (28278014097887232, 28278014064332800, 15, '2018-11-07 16:49:16', '2018-11-07 16:49:16', '2018-11-07 16:49:16', '2018-11-07 16:49:16');
INSERT INTO `xhx_re_user_role` VALUES (30369060814458880, 30369060768321536, 15, '2018-11-13 11:18:20', '2018-11-13 11:18:20', '2018-11-13 11:18:20', '2018-11-13 11:18:20');
INSERT INTO `xhx_re_user_role` VALUES (33274115272085504, 33274115259502592, 15, '2018-11-21 11:41:59', '2018-11-21 11:41:59', '2018-11-21 11:41:59', '2018-11-21 11:41:59');
INSERT INTO `xhx_re_user_role` VALUES (33274331794640896, 33274331786252288, 15, '2018-11-21 11:42:51', '2018-11-21 11:42:51', '2018-11-21 11:42:51', '2018-11-21 11:42:51');
INSERT INTO `xhx_re_user_role` VALUES (33274530399129600, 33274530323632128, 15, '2018-11-21 11:43:38', '2018-11-21 11:43:38', '2018-11-21 11:43:38', '2018-11-21 11:43:38');
INSERT INTO `xhx_re_user_role` VALUES (33301650726850560, 33301650714267648, 15, '2018-11-21 13:31:24', '2018-11-21 13:31:24', '2018-11-21 13:31:24', '2018-11-21 13:31:24');

-- ----------------------------
-- Table structure for xhx_risk_monitor
-- ----------------------------
DROP TABLE IF EXISTS `xhx_risk_monitor`;
CREATE TABLE `xhx_risk_monitor`  (
  `id` bigint(20) NOT NULL,
  `menuName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '警戒内容',
  `monitorPrice` double NOT NULL COMMENT '监控单价',
  `discount` double NOT NULL COMMENT '单价折扣',
  `createTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '风险内容' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_risk_monitor
-- ----------------------------
INSERT INTO `xhx_risk_monitor` VALUES (1, '工商信息', 35, 1, '2018-10-24 16:54:21', '2018-10-24 16:54:21');
INSERT INTO `xhx_risk_monitor` VALUES (2, '负面信息', 35, 1, '2018-10-24 16:54:22', '2018-10-24 16:54:22');
INSERT INTO `xhx_risk_monitor` VALUES (3, '舆情信息', 35, 1, '2018-10-24 16:54:27', '2018-10-24 16:54:27');

-- ----------------------------
-- Table structure for xhx_risk_order
-- ----------------------------
DROP TABLE IF EXISTS `xhx_risk_order`;
CREATE TABLE `xhx_risk_order`  (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `riskId` bigint(20) NULL DEFAULT NULL COMMENT '风险ID',
  `companyId` bigint(20) NULL DEFAULT NULL COMMENT '公司ID',
  `orderId` bigint(50) NULL DEFAULT NULL COMMENT '订单编号',
  `companyName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `roType` int(11) NULL DEFAULT NULL COMMENT '类别 1.客户 2.潜在客户 3.供应商 4.经销商 5.竞争对手',
  `startTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '开始时间',
  `endTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '结束时间',
  `createTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户风险警戒' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_risk_order
-- ----------------------------
INSERT INTO `xhx_risk_order` VALUES (1544517061507171, 3, 2, 50601137, 2018121139706531, '商丘铭鑫汽车保险代理有限公司', 2, '2018-12-13 18:27:28', '2018-12-13 18:27:28', '2018-12-13 18:27:28', '2018-12-13 18:27:28');
INSERT INTO `xhx_risk_order` VALUES (1544520276234523, 3, 1, 17958184, 2018121132781085, '大博医疗科技股份有限公司', 1, '2018-12-11 17:28:34', '2018-12-11 17:28:30', '2018-12-11 17:27:39', '2018-12-11 17:27:39');
INSERT INTO `xhx_risk_order` VALUES (25339502444810240, 3, 1, 1, 2018103011015810, '中国新兴工程建筑房地产开发总公司', 3, '2018-12-17 10:23:13', '2018-12-17 10:23:13', '2018-12-17 10:23:13', '2018-12-17 10:23:13');

-- ----------------------------
-- Table structure for xhx_risk_set_up
-- ----------------------------
DROP TABLE IF EXISTS `xhx_risk_set_up`;
CREATE TABLE `xhx_risk_set_up`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NULL DEFAULT NULL,
  `receive_mode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风险接收方式\r\n1 邮件\r\n2 APP消息\r\n3 微信\r\n',
  `mails` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接受邮件',
  `send_cycle` int(1) NULL DEFAULT NULL COMMENT '发送周期\r\n1 每周\r\n2 每月\r\n3 季度\r\n4 不需要\r\n',
  `createTime` timestamp(0) NULL DEFAULT NULL,
  `updateTime` timestamp(0) NULL DEFAULT NULL,
  `sendMonth` int(1) NULL DEFAULT NULL COMMENT '发送周期：每月(默认0：1启用，0不启用)',
  `sendWeek` int(1) NULL DEFAULT NULL COMMENT '发送周期：每周(默认0：1启用，0不启用)',
  `sendQuarter` int(1) NULL DEFAULT NULL COMMENT '发送周期：季度(默认0：1启用，0不启用)',
  `receiveMail` int(1) NULL DEFAULT NULL COMMENT '接受方式：邮件(默认0：1启用，0不启用)',
  `receiveAPP` int(1) NULL DEFAULT NULL COMMENT '接受方式：APP消息(默认0：1启用，0不启用)',
  `receiveWeChat` int(1) NULL DEFAULT NULL COMMENT '接受方式：微信(默认0：1启用，0不启用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '风险提示设置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_risk_set_up
-- ----------------------------
INSERT INTO `xhx_risk_set_up` VALUES (23183033742004224, 5, '12', '7890@h.com', 1, '2018-10-24 15:23:38', '2018-10-24 15:23:38', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `xhx_risk_set_up` VALUES (23183040473862144, 1, '1', '1515151@123', 1, '2018-10-24 15:23:40', '2018-10-24 15:23:40', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `xhx_risk_set_up` VALUES (23183044613640192, 4, '1', '1515151@123', 1, '2018-10-24 15:23:41', '2018-10-24 15:23:41', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `xhx_risk_set_up` VALUES (23910427570343936, 3, '13', 'ghfdghhg@hjh.com', 1, '2018-10-26 15:34:02', '2018-12-17 10:31:55', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xhx_role
-- ----------------------------
DROP TABLE IF EXISTS `xhx_role`;
CREATE TABLE `xhx_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `nameZh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isEnable` int(1) NULL DEFAULT 1,
  `createTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_role
-- ----------------------------
INSERT INTO `xhx_role` VALUES (1, 'ROLE_manager', '部门经理', 1, 1, '2018-10-08 10:25:59', '2018-10-08 10:26:07');
INSERT INTO `xhx_role` VALUES (2, 'ROLE_personnel', '人事专员', 2, 1, '2018-10-08 10:26:00', '2018-10-08 10:26:09');
INSERT INTO `xhx_role` VALUES (3, 'ROLE_recruiter', '招聘主管', 3, 1, '2018-10-08 10:26:02', '2018-10-08 10:26:10');
INSERT INTO `xhx_role` VALUES (4, 'ROLE_train', '培训主管', 4, 1, '2018-10-08 10:26:04', '2018-10-08 10:26:13');
INSERT INTO `xhx_role` VALUES (5, 'ROLE_performance', '薪酬绩效主管', 8, 1, '2018-10-08 10:26:06', '2018-10-08 10:26:14');
INSERT INTO `xhx_role` VALUES (6, 'ROLE_admin', '系统管理员', 5, 1, '2018-10-08 10:26:08', '2018-10-08 10:26:16');
INSERT INTO `xhx_role` VALUES (13, 'ROLE_test2', '测试角色2', 6, 1, '2018-10-08 10:26:09', '2018-10-08 10:26:17');
INSERT INTO `xhx_role` VALUES (14, 'ROLE_test1', '测试角色1', 7, 1, '2018-10-08 10:26:11', '2018-10-08 10:26:19');
INSERT INTO `xhx_role` VALUES (15, 'NODE_ordi', '普通会员', 9, 1, '2018-09-16 13:38:39', '2018-09-16 13:38:39');
INSERT INTO `xhx_role` VALUES (16, 'NODE_trial', '试用会员', 10, 1, '2018-09-16 13:38:34', '2018-09-16 13:38:34');
INSERT INTO `xhx_role` VALUES (17, 'NODE_vip', 'VIP会员', 8, 1, '2018-09-16 13:38:27', '2018-09-16 13:38:27');
INSERT INTO `xhx_role` VALUES (18, 'NODE_ordi_client', '普通会员', 11, 1, '2018-10-08 10:26:39', '2018-10-08 10:26:41');
INSERT INTO `xhx_role` VALUES (19, 'NODE_trial_client', '试用会员', 12, 1, '2018-09-21 10:46:22', '2018-09-21 10:46:22');
INSERT INTO `xhx_role` VALUES (20, 'NODE_vip_client', 'VIP会员', 13, 1, '2018-10-08 10:26:43', '2018-10-08 10:26:45');

-- ----------------------------
-- Table structure for xhx_user
-- ----------------------------
DROP TABLE IF EXISTS `xhx_user`;
CREATE TABLE `xhx_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码(加密)',
  `parentId` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `userType` int(1) NULL DEFAULT NULL COMMENT '用户类型',
  `bindMobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户所绑定的手机号',
  `lastLoginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) NULL DEFAULT NULL COMMENT '登录总次数',
  `profilePicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `enabled` int(1) NULL DEFAULT NULL COMMENT '是否启用',
  `createTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_user
-- ----------------------------
INSERT INTO `xhx_user` VALUES (1, 'admin', '$2a$10$82NCPBswGyqjyBsVDq/weOivgTDooCZPIXm.v5vM4hpWxGmRY8Afu', NULL, 1, '456', '192.168.11.123', '2018-08-31 15:30:25', 1, NULL, 1, '2018-08-31 15:30:54', '2018-08-31 15:30:56');
INSERT INTO `xhx_user` VALUES (2, 'zhangjun', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', 1544606049554913, 1, '15612312300', '106.37.228.170', '2018-12-12 14:10:16', 23, NULL, 1, '2018-08-31 15:37:14', '2018-12-12 17:14:58');
INSERT INTO `xhx_user` VALUES (3, 'wusujun', '$2a$10$GSfPRZxtZ4oblqQpIGZFVuJz424N3Kja5NM21gj0NNW9ssV2aasx.', 119, 2, '13910541231', '106.37.228.170', '2018-12-17 13:12:33', 379, NULL, 1, '2018-08-31 15:37:14', '2018-08-31 15:37:17');
INSERT INTO `xhx_user` VALUES (4, 'suxiaoxiong', '$2a$10$FeSuGbLPGfxbQTCbWH8wLue/fy2mzlcA8Qi1/MhthnjVa9a6zKVIa', 119, 2, '18956123658', '', NULL, 1, NULL, 1, '2018-08-31 15:37:14', '2018-08-31 15:37:17');
INSERT INTO `xhx_user` VALUES (282780140643328, 'xiaoming', '$2a$10$.EwrBhY7Uu7W0.aTGGefZuupsZSmmOJA87Jy6ScAzolr8FwyqIUWO', 119, 2, '15612365123', NULL, NULL, 0, NULL, 1, '2018-11-07 16:49:16', '2018-11-07 16:49:16');
INSERT INTO `xhx_user` VALUES (17363094925873152, 'wangwu', '$2a$10$Gn1I0mCwacGSKfTE9sY0jOzHn//QUnhpu0ZYvjlnF/gCRFt1H8S7e', 119, 2, '', NULL, NULL, 0, NULL, 1, '2018-10-08 13:57:16', '2018-10-08 13:57:16');
INSERT INTO `xhx_user` VALUES (17363401760182272, 'zhaoliu', '$2a$10$Z/0.yYHuJ8dTK/AbR56pKeVWqXLWjE0xWJPDq7VdZsG6ne00phee2', 119, 2, '', NULL, NULL, 0, NULL, 0, '2018-10-08 13:58:30', '2018-10-08 13:58:30');
INSERT INTO `xhx_user` VALUES (17363848696827904, 'wangxiaowu', '$2a$10$N3j8Zg1LPM8K/n7CMoAAMersSicaEeRoQMX9Ik67zlDkUdDHqv9kC', 119, 2, '15612365789', NULL, NULL, 0, NULL, 0, '2018-10-08 14:00:16', '2018-10-08 14:00:16');
INSERT INTO `xhx_user` VALUES (17366790220943360, 'zhaosi', '$2a$10$C8wkAdhuFfh9g/xdduwJkuxR5qJ4NgQpTURKIA1BcVJ2gec5xieZm', 3, 2, '', NULL, NULL, 0, NULL, 1, '2018-10-08 14:11:57', '2018-10-08 14:11:57');
INSERT INTO `xhx_user` VALUES (30369060768321536, 'xiaohongmao', '$2a$10$uykTsSKObdkmkMV8jkPwMusNoWj2w6/3hfm0pFreA7V0uAFx1oaFu', 119, 2, '15612312300', NULL, NULL, 0, NULL, 0, '2018-11-13 11:18:20', '2018-11-13 11:18:20');
INSERT INTO `xhx_user` VALUES (33301650714267648, 'hahahhah', '$2a$10$75KeNKdwwJMdWG3bfpLiPOQ8gsJwCI6fms8uBiJf30.zH5KMVXOLe', 119, 2, '17215632100', NULL, NULL, 0, NULL, 1, '2018-11-21 13:31:24', '2018-11-21 13:31:24');

-- ----------------------------
-- Table structure for xhx_user_visit_api
-- ----------------------------
DROP TABLE IF EXISTS `xhx_user_visit_api`;
CREATE TABLE `xhx_user_visit_api`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位公司',
  `loginCount` int(11) NOT NULL COMMENT '访问总次数',
  `isEnable` int(1) NOT NULL COMMENT '是否启用',
  `loginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户IP',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户调用API表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_user_visit_api
-- ----------------------------
INSERT INTO `xhx_user_visit_api` VALUES (20355504270348288, '宋志林', '新华信', 61, 1, '192.168.11.90', '2018-10-16 20:08:03', '2018-10-23 18:33:51', 'eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbklwIjoiMTkyLjE2OC4xMS45MCIsImNvbXBhbnkiOiLmlrDljY7kv6EiLCJpZCI6MjAzNTU1MDQyNzAzNDgyODgsInVzZXJuYW1lIjoi5a6L5b-X5p6XIn0.ax1gljrm9kzK-g4G_79q_LcKouarBwbLSGk_pyy7LC8');

-- ----------------------------
-- Table structure for xhx_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `xhx_userinfo`;
CREATE TABLE `xhx_userinfo`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `parentId` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `userType` int(1) NULL DEFAULT NULL COMMENT '用户类型',
  `nameZh` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户中文名称',
  `nameEn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mobilePhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通信地址',
  `birthDay` date NULL DEFAULT NULL COMMENT '出生日期',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别,1男，2女，3其他',
  `company` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位公司',
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `companyid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司ID',
  `occupation` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业/职业',
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_userinfo
-- ----------------------------
INSERT INTO `xhx_userinfo` VALUES (1, 1, NULL, 1, '佛爷', 'zjg', '18610027352', '18610027352', 'xxxxxxx', '1990-01-01', 1, '新华信', '技术部', '', '软件工程', 'java工程师', '2018-09-30 16:30:02', '2018-08-31 15:41:37', NULL);
INSERT INTO `xhx_userinfo` VALUES (2, 119, NULL, 2, '张军', 'zhangjun', '15612312300', '15612312300', '', '1995-08-18', 1, '新华信', '技术部', '', '软件工程', 'java工程师', '2018-11-26 17:20:42', '2018-08-31 15:41:37', '1234@xhxdata.com');
INSERT INTO `xhx_userinfo` VALUES (3, 3, 119, 2, '吴苏君', 'wusujun', '13521797425', '13521797425', '', '1995-02-02', 1, '新华信', '技术部', '', '软件工程', 'java工程师', '2018-11-14 16:21:48', '2018-08-31 15:41:37', '123@xhxdata.com');
INSERT INTO `xhx_userinfo` VALUES (4, 4, NULL, 2, '孙小雄', 'sunxiaoxiong', '', '', '', '1990-01-01', 1, '新华信', '技术部', '', '软件工程', 'java工程师', '2018-08-31 15:41:35', '2018-08-31 15:41:37', NULL);
INSERT INTO `xhx_userinfo` VALUES (17363095055896576, 17363094925873152, 119, NULL, '王五', NULL, NULL, '', NULL, NULL, NULL, NULL, '技术部', NULL, NULL, NULL, '2018-11-12 18:38:35', '2018-10-08 13:57:16', 'ghagf@hjf.con');
INSERT INTO `xhx_userinfo` VALUES (17363401814708224, 17363401760182272, 119, NULL, '赵六', NULL, NULL, '', NULL, NULL, NULL, NULL, '研发部', NULL, NULL, NULL, '2018-11-12 18:38:48', '2018-10-08 13:58:30', 'hjkh@ajkh.com');
INSERT INTO `xhx_userinfo` VALUES (17363848742965248, 17363848696827904, 119, NULL, '王小五', NULL, NULL, '15612365789', NULL, NULL, NULL, NULL, '技术部', NULL, NULL, NULL, '2018-11-12 18:38:53', '2018-11-13 11:10:14', 'njdh@hjhrf.com');
INSERT INTO `xhx_userinfo` VALUES (17366790279663616, 17366790220943360, 3, NULL, '赵四', NULL, NULL, '', NULL, NULL, NULL, NULL, '研发部', NULL, NULL, NULL, '2018-10-08 14:11:57', '2018-10-08 14:11:57', 'hfh@fjh.com');
INSERT INTO `xhx_userinfo` VALUES (28278014269853696, 282780140643328, 119, NULL, '小明', NULL, NULL, '', NULL, NULL, NULL, NULL, '技术部', NULL, NULL, NULL, '2018-11-12 16:22:10', '2018-11-07 16:49:16', 'ghjk@jkh.com');
INSERT INTO `xhx_userinfo` VALUES (30369060827041792, 30369060768321536, 119, NULL, '小红帽', NULL, NULL, '15612312300', NULL, NULL, NULL, NULL, '市场部', NULL, NULL, NULL, '2018-11-13 11:18:20', '2018-11-21 13:26:33', 'hdjfh@ghdfgy.com');
INSERT INTO `xhx_userinfo` VALUES (33301650731044864, 33301650714267648, 119, NULL, '哈哈哈哈', NULL, NULL, '17215632100', NULL, NULL, NULL, NULL, '活动即可更换', NULL, NULL, NULL, '2018-11-21 13:31:24', '2018-11-21 13:31:24', 'gf@hj.com');

-- ----------------------------
-- Table structure for xhx_wallet_record
-- ----------------------------
DROP TABLE IF EXISTS `xhx_wallet_record`;
CREATE TABLE `xhx_wallet_record`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户ID',
  `balance` double NOT NULL COMMENT '余额',
  `giveMoney` double NULL DEFAULT NULL COMMENT '赠送金额',
  `rechMoney` double NULL DEFAULT NULL COMMENT '充值金额',
  `consume` double NULL DEFAULT NULL COMMENT '消费金额',
  `operateUser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作用户',
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `isavailable` int(1) NOT NULL COMMENT '是否可开发票',
  `invoiceStatus` int(1) NOT NULL COMMENT '开票状态：未开发票 已开发票',
  `consume_projects` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消费项目',
  `recordNo` bigint(20) NULL DEFAULT NULL COMMENT '订单编号',
  `paymentType` int(1) NULL DEFAULT NULL COMMENT '付款方式\r\n1 微信\r\n2 支付宝\r\n3 银行卡\r\n4 信用卡\r\n\r\n',
  `flowNumber` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款流水号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '充值消费记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_wallet_record
-- ----------------------------
INSERT INTO `xhx_wallet_record` VALUES (1, 1, 1, 1, 1, 1, '1', '2018-09-20 17:12:40', '2018-09-19 11:41:36', 1, 0, '1', 1, 1, '1');
INSERT INTO `xhx_wallet_record` VALUES (3, 2, 2, 2, 2, 2, '2', '2018-09-20 17:13:09', '2018-09-19 13:50:53', 0, 1, '2', 2, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (4, 3, 3, 3, 3, 3, '3', '2018-09-21 10:45:41', '2018-09-21 10:45:45', 1, 1, '1', 1, 1, '1');
INSERT INTO `xhx_wallet_record` VALUES (5, 4, 4, 4, 4, 4, '4', '2018-09-21 10:46:12', '2018-09-21 10:46:14', 1, 1, '4', 4, 4, '4');
INSERT INTO `xhx_wallet_record` VALUES (6, 10, 10, 10, 10, 10, '10', '2018-09-21 10:46:48', '2018-09-21 10:46:50', 1, 0, '1', 1, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (7, 4, 10, 10, 10, 10, '10', '2018-10-26 16:45:24', '2018-09-21 10:46:50', 1, 0, '1', 1, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (8, 10, 10, 10, 10, 10, '10', '2018-09-21 10:46:48', '2018-09-21 10:46:50', 1, 0, '1', 1, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (9, 9, 9, 9, 9, 9, '9', '2018-09-21 10:46:21', '2018-09-21 10:46:23', 0, 0, '2', 2, 1, '2');
INSERT INTO `xhx_wallet_record` VALUES (10, 10, 10, 10, 10, 10, '10', '2018-09-21 10:46:48', '2018-09-21 10:46:50', 1, 0, '1', 1, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (11, 11, 11, 11, 11, 11, '11', '2018-09-21 10:47:42', '2018-09-21 10:47:45', 1, 0, '1', 0, 2, '1');
INSERT INTO `xhx_wallet_record` VALUES (12, 12, 12, 12, 12, 12, '12', '2018-09-21 10:48:10', '2018-09-21 10:48:13', 0, 1, '0', 1, 1, '2');
INSERT INTO `xhx_wallet_record` VALUES (13, 10, 10, 10, 10, 10, '10', '2018-09-21 10:46:48', '2018-09-21 10:46:50', 1, 0, '1', 1, 2, '2');
INSERT INTO `xhx_wallet_record` VALUES (12659743592157184, 18, 185.55, 155, 88, 2, '詹姆斯', '2018-09-26 10:33:10', '2018-09-26 10:33:00', 1, 15, '植发', NULL, NULL, NULL);
INSERT INTO `xhx_wallet_record` VALUES (12674885562273792, 119, 185.55, 155, 88, 3, '韦德', '2018-10-26 16:43:33', '2018-09-26 10:32:57', 1, 2, '植发', NULL, NULL, NULL);
INSERT INTO `xhx_wallet_record` VALUES (14473906958241792, 18, 185.55, 155, 88, 15, '詹姆斯', '2018-09-30 14:36:40', '2018-09-30 14:36:40', 1, 2, '植发', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xhx_white_list
-- ----------------------------
DROP TABLE IF EXISTS `xhx_white_list`;
CREATE TABLE `xhx_white_list`  (
  `id` bigint(20) NOT NULL,
  `loginIp` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户IP',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户调用API表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of xhx_white_list
-- ----------------------------
INSERT INTO `xhx_white_list` VALUES (1, '192.168.11.90', NULL, NULL);
INSERT INTO `xhx_white_list` VALUES (2, '192.168.11.133', NULL, NULL);
INSERT INTO `xhx_white_list` VALUES (3, '192.168.11.93', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
