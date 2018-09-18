/*
 Navicat Premium Data Transfer

 Source Server         : AMS
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : ams01

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001
 
 Author                : weiJohn
 version               :0.02
*/

-- 创建数据库 ，若创建过 则不用创建了，不需要这句
create database `ams` default character set utf8 collate utf8_general_ci;

-- 使用数据库
use ams;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

--  Table structure 
-- 一般建表时候，创建时间用datetime，更新时间用timestamp。

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `NICK_NAME` varchar(100) DEFAULT NULL  COMMENT '用户昵称',
  `AVATAR` varchar(100) DEFAULT NULL  COMMENT '用户头像',
  `GENDER` varchar(8) DEFAULT NULL  COMMENT '用户性别,0：女 1：男',
  `FROM` varchar(8) DEFAULT NULL  COMMENT '用户来源，微信或支付宝,0：支付宝 1：微信',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `WX_OPENID` varchar(200) NOT NULL COMMENT '用户微信应用唯一标识',
  `CITY` varchar(100) DEFAULT NULL COMMENT '用户注册城市',
  `LOCATION` varchar(100) DEFAULT NULL COMMENT '用户注册城市具体位置',
  `POINT` int(11) NOT NULL DEFAULT 0 COMMENT '用户积分',
  `TELEPHONE` varchar(50) NOT NULL COMMENT '用户电话',
  `APP_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户应用状态：0：正常态；1：租借态；2：欠费态',
  `IS_STAFF` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是本公司员工：0：不是；1：是',
  `IS_AGENT` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是代理商：0：不是；1：是',
  `IS_VIP`  varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是vip用户: 0:不是 1：是',
  `FREE_TIME` varchar(40) NOT NULL DEFAULT '0' COMMENT '用户如果是vip用户的话，免费充电时长,单位分钟',
  `SKEY` varchar(200) NOT NULL DEFAULT '0'  COMMENT '用户登录态',
  `WX_UIONID` varchar(200) DEFAULT NULL  COMMENT '用户微信开放平台环境里，唯一ID为UnionId',
  `IS_UNSUBSCRIBE` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否取消关注：0：没有 1：取消',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `user_wx_openid` (`WX_OPENID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 向t_user插入一条测试数据
INSERT INTO `t_user` VALUES ('01', 'test', '头像', '1', '1', '2018-09-13 12:12:12', 
  '0123456789', '深圳', '深圳宝安黄田', '88', '18603059798','0','0','0',
  '0', '300', 'Skey9876543210', 'UIONID654987321', '0', '这是测试插入的记录','2018-09-15 12:12:12');

-- ----------------------------------
--  Table structure for `t_account`
-- ----------------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
	`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
	`ACCOUNT_DEPOSIT` varchar(50) NOT NULL DEFAULT '0' COMMENT '账户押金',
	`IS_REFUND_DEPOSIT` varchar(8) NOT NULL DEFAULT '0' COMMENT '是否退押金：0：不退 1：退还 默认不退押金',
	`ACCOUNT_BALANCE` varchar(50) NOT NULL DEFAULT '0' COMMENT '账户余额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建账户时间',
  `UPDATE_TIME` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

-- 向t_account插入一条测试数据
INSERT INTO `t_account` VALUES ('01', '99', '0', '268', '2018-09-13 13:13:13','2018-09-15 12:12:12');


-- -- ----------------------------------------------------------------
--  Table structure for `t_user_account`  中间表：用户与账户关联的中间表
-- -------------------------------------------------------------------
DROP TABLE IF EXISTS `t_user_account`;
CREATE TABLE `t_user_account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `U_ID` int(11) DEFAULT NULL COMMENT '用户id',
  `A_ID` int(11) DEFAULT NULL COMMENT '账户id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与账户映射表';


-- 在 t_account表 中为 uid 列添加名为 user_account 的外键，
-- 且此外键的参照为 t_user 表的 id 列，关联的操作为删除和更新
-- alter table `t_account` 
-- add constraint `user_account` foreign key (`uid`) 
-- references `t_user`(`id`) on delete cascade on update cascade;


-- ----------------------------------
--  Table structure for `t_order`
-- ----------------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
	`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
	`ORDER_NUM` varchar(100) NOT NULL COMMENT '订单编号',
	`CREATE_TIME` datetime DEFAULT NULL COMMENT '订单创建时间',
	`END_TIME` datetime DEFAULT NULL COMMENT '订单结束时间',
	`PAY_AMOUNT` varchar(50) DEFAULT NULL COMMENT '订单金额',
	`PAY_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '订单是否支付完成状态位:0表示未支付;1表示支付完成',
	`POWER_BANK_BORROW_PLACE` varchar(300) DEFAULT NULL COMMENT '借充电宝的地点，即订单创建地点',
	`POWER_BANK_BACK_PLACE` varchar(300) DEFAULT NULL COMMENT '归还电宝的地点，即订单完成(即订单结束)地点,但此时并不一定支付了订单',
	`POWER_BANK_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '订单中的充电宝的状态：0：租借中 1：归还成功',
	`POWER_BANK_ID` varchar(200) NOT NULL  COMMENT '充电宝ID',
	`BOX_CHARGING_ID` varchar(200) NOT NULL COMMENT '充电箱ID',
	`PAY_TIME` datetime DEFAULT NULL COMMENT '订单支付完成时间,标志着订单状态位此时变为1',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 向t_order插入一条测试数据
INSERT INTO `t_order` VALUES ('01', 'test0123456789', '2018-09-13 14:14:14', '2018-09-13 23:23:23', '2', '1','东莞','深圳','1','qdpb001',
  'qdcb001','2018-09-16 13:13:30');


-- -- ----------------------------------------------------------------
--  Table structure for `t_account_order`  中间表：账户与订单关联的中间表
-- -------------------------------------------------------------------
DROP TABLE IF EXISTS `t_account_order`;
CREATE TABLE `t_account_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `A_ID` int(11) DEFAULT NULL COMMENT '账户id',
  `O_ID` int(11) DEFAULT NULL COMMENT '订单id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户与订单映射表';


-- 在 t_order 中为 aid 列添加名为 account_order 的外键，
-- 且此外键的参照为 t_account 表的 id 列，关联的操作为删除和更新
-- alter table `t_order` 
-- add constraint `account_order` foreign key (`aid`) 
-- references `t_account`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_water_bills`
-- --------------------------------------
DROP TABLE IF EXISTS `t_water_bills`;
CREATE TABLE `t_water_bills` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `AMOUNT` varchar(200) NOT NULL COMMENT '流水金额',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT  '流水产生时间',
  `TYPE` varchar(8) DEFAULT NULL COMMENT '流水类型：0表示扣费；1表示充值；3：退还押金',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水表';

-- 向t_order插入一条测试数据
INSERT INTO `t_water_bills` VALUES ('01', 'test222', '2018-09-13 15:15:15', '0');


-- -- ----------------------------------------------------------------
--  Table structure for `t_account_order`  中间表：订单与流水映射表
-- -------------------------------------------------------------------
DROP TABLE IF EXISTS `t_order_water_bills`;
CREATE TABLE `t_order_water_bills` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `O_ID` int(11) DEFAULT NULL COMMENT '订单id',
  `WB_ID` int(11) DEFAULT NULL COMMENT '流水id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单与流水映射表';

-- 在 t_water_bills 中为 oid 列添加名为 order_water_bills 的外键，
-- 且此外键的参照为 t_order 表的 id 列，关联的操作为删除和更新
-- alter table `t_water_bills` 
-- add constraint `order_water_bills` foreign key (`oid`) 
-- references `t_order`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_charging_box`
-- --------------------------------------
DROP TABLE IF EXISTS `t_charging_box`;
CREATE TABLE `t_charging_box` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '充电箱id',
  `BOX_CUSTOMER_ID` varchar(50) NOT NULL COMMENT '充电箱Customer ID，如0x4D',
  `BOX_DATE` varchar(30) NOT NULL COMMENT '充电箱出厂时间，如1710',
  `BOX_SN` varchar(100) NOT NULL COMMENT '充电箱SN',
  `LOCATION` varchar(300) NOT NULL COMMENT '充电箱摆放位置',
  `STATUS` varchar(8) NOT NULL COMMENT '充电箱状态：0：离线 1:在线(正常运行) 2：故障',
  `MODEL` varchar(100) NOT NULL COMMENT '充电箱型号',
  `SOFTWARE_VERSION` varchar(100) NOT NULL COMMENT '充电箱软件版本号',
  `BUSINESS` varchar(50) NOT NULL COMMENT '充电箱所处位置的业态，比如商场，酒吧等',
  `BORROW_NUM` varchar(20) NOT NULL COMMENT '充电箱里充电宝已借出多少台',
  `POWER_BANK_NUM` varchar(20) NOT NULL COMMENT '充电箱总共有多少台充电宝，这个是一开始放进去的数量',
  `CAN_BORROW_NUM` varchar(20) NOT NULL COMMENT '充电箱里充电宝还有多少台可借',
  `CAN_BACK_NUM` varchar(20) NOT NULL COMMENT '可还到充电箱的充电宝数量',
  `MALFUNCTION_NUM` varchar(20) NOT NULL COMMENT '充电箱里故障充电宝数量',
  `SPEAKER_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '充电箱喇叭状态：0关闭，1打开',
  `SIM_CCID` varchar(100) NOT NULL COMMENT '充电箱SIM卡CCID',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电箱表';

-- 向t_charging_box插入一条测试数据
INSERT INTO `t_charging_box` VALUES ('01', 'test0x4d', '2018-03-05', 'sfe3333','宝安中心壹方城','1','3s5d','0.01','商场'
,'5','10','5','5','0','1','dd433','2018-09-13 15:15:15','2018-09-15 12:12:12');


-- -------------------------------------------
--  Table structure for `t_pricing_type_cb`
-- -------------------------------------------
DROP TABLE IF EXISTS `t_price_type_cb`;
CREATE TABLE `t_price_type_cb` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '充电箱定价策略表id',
  `FREE_TIME` varchar(40) NOT NULL DEFAULT '0' '代理商在小程序设置的免费时长，单位分钟,免费时长范围:5-30分钟',
  `PRICE_PER_HOUR` varchar(20) NOT NULL COMMENT '每小时价格,单位元,范围:0-10元/小时',
  `TOP_PRICE_PER_DAY` varchar(20) NOT NULL COMMENT '每日封顶价格，单位元，范围:0-50元/天',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电箱定价策略表';

-- 向t_price_type_cb插入一条测试数据
INSERT INTO `t_price_type_cb` VALUES ('01', 'test0', '2', '40','2018-09-14 15:15:15','2018-09-15 12:12:12');


-- -- ----------------------------------------------------------------------------
--  Table structure for `t_chargingBox_priceType`  中间表：充电箱表与定价策略表映射表
-- -------------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_chargingBox_priceType`;
CREATE TABLE `t_chargingBox_priceType` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `CB_ID` int(11) DEFAULT NULL COMMENT '充电箱表id',
  `PT_ID` int(11) DEFAULT NULL COMMENT '充电箱定价策略表id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电箱表与定价策略表映射表';


-- --------------------------------------
--  Table structure for `t_power_bank`
-- --------------------------------------
DROP TABLE IF EXISTS `t_power_bank`;
CREATE TABLE `t_power_bank` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '充电宝id',
  `LOCK_ID` varchar(50) NOT NULL COMMENT '充电宝锁位ID',
  `LOCK_STATUS` varchar(8) NOT NULL COMMENT '充电宝锁位状态：0关闭，1开启',
  `LOCK_OPERATION_STATUS` varchar(8) NOT NULL COMMENT '充电宝锁位操作状态：0操作失败，1操作成功',
  `PB_CUSTOMER_ID` varchar(50) NOT NULL COMMENT '充电宝Customer ID',
  `PB_MODEL` varchar(100) NOT NULL COMMENT '充电宝型号',
  `PB_DATE` varchar(30) NOT NULL COMMENT '充电宝出厂日期',
  `PB_SN` varchar(100) NOT NULL COMMENT '充电宝SN',
  `PB_CAPACITY` varchar(20) NOT NULL  COMMENT '充电宝电量：0x1(0-19%) 0x2(20%-39%) 0x3(40%-59%) 0x4(60%-79%) 0x5(80%-99%) 0x6(100%',
  `PB_STATUS` varchar(20) NOT NULL COMMENT '充电宝状态：0借出，1在位',
  `PB_PRIORITY` varchar(8) NOT NULL COMMENT '充电宝电量是否大于80%，如果大于可优先借出，0否，1是',
  `BORROW_TIMES` varchar(50) NOT NULL DEFAULT '0' COMMENT '充电宝被借出次数',
  `TOTAL_USE_TIME` varchar(50) NOT NULL DEFAULT '0' COMMENT '充电宝总的使用时间,单位分钟',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电宝表';


-- 向t_power_bank插入一条测试数据
INSERT INTO `t_power_bank` VALUES ('01', 'test34454', '1', '1','3434dfdef','345m','2018-09-14','sn44545','0x5'
,'0','1','25','3333','2018-09-14 15:15:15','2018-09-15 12:12:12');


-- -- ----------------------------------------------------------------------------
--  Table structure for `t_chargingBox_powerBank`  中间表：充电箱表与充电宝表映射表
-- -------------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_chargingBox_powerBank`;
CREATE TABLE `t_chargingBox_powerBank` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `CB_ID` int(11) DEFAULT NULL COMMENT '充电箱表id',
  `PB_ID` int(11) DEFAULT NULL COMMENT '充电宝表id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电箱表与充电宝表映射表';


-- 在 t_power_bank 中为 cbid 列添加名为 chargingBox_powerBank 的外键，
-- 且此外键的参照为 t_charging_box 表的 id 列，关联的操作为删除和更新
-- alter table `t_power_bank` 
-- add constraint `chargingBox_powerBank` foreign key (`cbid`) 
-- references `t_charging_box`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_agent`
-- --------------------------------------
DROP TABLE IF EXISTS `t_agent`;
CREATE TABLE `t_agent` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '代理商id',
  `REAL_NAME` varchar(100) NOT NULL COMMENT '代理商真实姓名',
  `CREDENTIALS_SALT` varchar(300) NOT NULL COMMENT '代理商加密盐，用于加密用户名和密码',
  `USER_NAME` varchar(100) NOT NULL COMMENT '代理商用户名',
  `PASSWORD` varchar(200) NOT NULL COMMENT '代理商密码，用于登录小程序',
  `ID_NUMBER` varchar(100) NOT NULL COMMENT '代理商身份证号',
  `LEVEL` varchar(50) NOT NULL DEFAULT '一级' COMMENT '代理商级别:1:一级 2:二级 3:三级 4:四级',
  `PARENT_AGENT_ID` varchar(100)  DEFAULT NULL COMMENT '父代理商id，默认为null',
  `SHARGING_RATIO` varchar(30) NOT NULL DEFAULT '0' COMMENT '代理商分成比例',
  `SERVICE_CHARGES` varchar(100) DEFAULT '0' COMMENT '代理商服务费',
  `CAN_WITHDRAW` varchar(8) DEFAULT '0' COMMENT '代理商提现: 0:不可提现 1：可提现',
  `CHARGING_BOX_NUM` varchar(50) DEFAULT '0' COMMENT '代理商拥有的充电箱数量',
  `FROZEN_AMOUNT` varchar(100) DEFAULT '0' COMMENT '代理商冻结金额',
  `ORDER_NUM` varchar(100) DEFAULT '0' COMMENT '代理商总的订单数',
  `TOTAL_REVENUE` varchar(100) DEFAULT '0' COMMENT '代理商总收益',
  `TOTAL_SHARE` varchar(100) DEFAULT '0' COMMENT '代理商总分成',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间,即成为代理商时间',
  `LEAVE_TIME` datetime DEFAULT NULL  COMMENT '解除代理商身份时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商表';


-- 向t_agent插入一条测试数据
INSERT INTO `t_agent` VALUES ('01', 'testzxw', '1fgr66456sty567', '后瑞张学友','3434dfdef','511021479899889','1','pidw','0'
,'0','0','0','0','0','0','0','2018-09-14 15:15:15','2038-09-14 15:15:15','2018-09-15 12:12:12');


-- --------------------------------------
--  Table structure for `t_agent_withdrawal`
-- --------------------------------------
DROP TABLE IF EXISTS `t_agent_withdrawal`;
CREATE TABLE `t_agent_withdrawal` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '代理商提现表id',
  `WITHDRAWAL_NO` varchar(200) NOT NULL COMMENT '提现订单编号',
  `AMOUNT` varchar(200) NOT NULL COMMENT '提现金额,单位元',
  `STATUS` varchar(8) NOT NULL COMMENT '提现状态: 0:失败  1：审核中 2：成功',
  `CREATE_TIME` datetime DEFAULT NULL  COMMENT '创建时间,即发起提现时间',
  `END_TIME` datetime DEFAULT NULL  COMMENT '完成(失败或成功都算完成)提现时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商提现表';

-- 向t_agent_withdrawal插入一条测试数据
INSERT INTO `t_agent_withdrawal` VALUES ('01', 'testNOzxw20180915160200258', '700000','2', 
  '2018-09-14 15:15:15','2018-09-17 15:15:15');


-- -- ----------------------------------------------------------------------------
--  Table structure for `t_agent_agentWithdrawal`  中间表：代理商表与代理商提现表映射表
-- -------------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_agent_agentWithdrawal`;
CREATE TABLE `t_agent_agentWithdrawal` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `A_ID` int(11) DEFAULT NULL COMMENT '代理商表id',
  `AW_ID` int(11) DEFAULT NULL COMMENT '代理商提现表id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商表与代理商提现表映射表';


-- --------------------------------------
--  Table structure for `t_shop`
-- --------------------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `SHOP_NO` varchar(100) NOT NULL COMMENT '商户编号',
  `LOGO` varchar(100) DEFAULT NULL COMMENT '商户logo',
  `NAME` varchar(100) NOT NULL COMMENT '商户名',
  `ADDRESS` varchar(200) NOT NULL COMMENT '商户地址',
  `CITY` varchar(30) DEFAULT NULL COMMENT '商户所在城市',
  `LONGITUDE` varchar(50) NOT NULL COMMENT '商户所在位置经度',
  `LATITUDE` varchar(50) NOT NULL COMMENT '商户所在位置维度',
  `STAR_LEVEL` varchar(30) DEFAULT NULL COMMENT '星级',
  `AREA_LABEL` varchar(100) DEFAULT NULL COMMENT '区域标签',
  `CATEGORY_LABEL` varchar(100) DEFAULT NULL COMMENT '品类标签',
  `LOCATION_TAG` varchar(100) DEFAULT NULL COMMENT '位置标签，比如商场楼上',
  `BUSINESS` varchar(50) DEFAULT NULL COMMENT '业态，比如车站等',
  `CONTRACT_PERSON_NAME` varchar(50) NOT NULL COMMENT '联系人姓名',
  `CONTRACT_PERSON_PHONE` varchar(50) NOT NULL COMMENT '联系人电话',
  `ID_NUMBER` varchar(100) DEFAULT NULL COMMENT '联系人身份证号',
  `SHOP_PHOTO` varchar(100) NOT NULL COMMENT '店面照片存放地址',
  `CONTRACT_PHOTO` varchar(100) NOT NULL COMMENT '合同照片存放地址',
  `BANK_ACCOUNT` varchar(100) NOT NULL COMMENT '银行账户',
  `START_BUSINESS_TIME` varchar(20) DEFAULT NULL COMMENT '营业开始时间',
  `END_BUSINESS_TIME` varchar(20) DEFAULT NULL COMMENT '营业结束时间',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间,即成为商户时间',
  `LEAVE_TIME` datetime DEFAULT NULL COMMENT '解除商户身份时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '表相关数据最后一次更新时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表';

-- 向t_agent插入一条测试数据
INSERT INTO `t_shop` VALUES ('01', 'tesNO33432342', 'logo', '砂锅粥','后瑞','深圳',
  '115°0’E','30°0’N','※※※','商业街','饮食','传奇一楼','后瑞地铁站附近','zxw','911110112114',
  '25648484848','~/qd/device/pic/shop_photo.jpg','~/qd/device/pic/contract_photo.jpg',
  '15484848','08:00','22:00','2018-09-14 15:15:15','2058-09-14 15:15:15','2018-09-15 12:12:12');


-- -- ----------------------------------------------------------------------------
--  Table structure for `t_agent_shop`  中间表：代理商表与商户表映射表
-- -------------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_agent_shop`;
CREATE TABLE `t_agent_shop` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `A_ID` int(11) DEFAULT NULL COMMENT '代理商表id',
  `S_ID` int(11) DEFAULT NULL COMMENT '商户表id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商表与商户表映射表';

-- -- ----------------------------------------------------------------------------
--  Table structure for `t_shop_chargingBox`  中间表：商户表与充电箱表映射表
-- -------------------------------------------------------------------------------
DROP TABLE IF EXISTS `t_shop_chargingBox`;
CREATE TABLE `t_shop_chargingBox` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表id',
  `S_ID` int(11) DEFAULT NULL COMMENT '商户表id',
  `CB_ID` int(11) DEFAULT NULL COMMENT '充电箱表id',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表与充电箱表映射表';



-- 开启外键约束
SET FOREIGN_KEY_CHECKS = 1   


