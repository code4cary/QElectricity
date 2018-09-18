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
  `GENDER` varchar(8) DEFAULT NULL  COMMENT '用户性别',
  `FROM` varchar(100) DEFAULT NULL  COMMENT '用户来源，微信或支付宝',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `WX_OPENID` varchar(200) NOT NULL COMMENT '用户微信应用唯一标识',
  `CITY` varchar(100) DEFAULT NULL COMMENT '用户注册城市',
  `LOCATION` varchar(100) DEFAULT NULL COMMENT '用户注册城市具体位置',
  `POINT` int(11) NOT NULL DEFAULT 0 COMMENT '用户积分',
  `TELEPHONE` varchar(50) NOT NULL COMMENT '用户电话',
  `APP_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户应用状态：0：正常态；1：租借态；2：欠费态',
  `IS_STAFF` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是本公司员工：0：不是；1：是',
  `IS_AGENT` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是代理商：0：不是；1：是',
  `IS_VIP`  varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否是vip用: 0:不是 1：是',
  `FREE_TIME` varchar(40) NOT NULL DEFAULT '0' COMMENT '用户如果是vip用户的话，免费充电时长',
  `SKEY` varchar(200) NOT NULL DEFAULT '0'  COMMENT '用户登录态',
  `WX_UIONID` varchar(200) DEFAULT NULL  COMMENT '用户微信开放平台环境里，唯一ID为UnionId',
  `IS_UNSUBSCRIBE` varchar(8) NOT NULL DEFAULT '0' COMMENT '用户是否取消关注：0：没有 1：取消',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '用户描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `user_wx_openid` (`WX_OPENID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ----------------------------------
--  Table structure for `t_account`
-- ----------------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
	`ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
	`UID` int(11) NOT NULL COMMENT '账户表关联用户表的外键',
	`ACCOUNT_DEPOSIT` varchar(50) NOT NULL DEFAULT '0' COMMENT '账户押金',
	`IS_REFUND_DEPOSIT` varchar(8) NOT NULL DEFAULT '0' COMMENT '是否退押金：0：不退 1：退还 默认不退押金',
	`ACCOUNT_BALANCE` varchar(50) NOT NULL DEFAULT '0' COMMENT '账户余额',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建账户时间，默认当前时间',
   KEY `UID`(`UID`),
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户表';

-- 在 t_account表 中为 uid 列添加名为 user_account 的外键，
-- 且此外键的参照为 t_user 表的 id 列，关联的操作为删除和更新
alter table `t_account` 
add constraint `user_account` foreign key (`uid`) 
references `t_user`(`id`) on delete cascade on update cascade;


-- ----------------------------------
--  Table structure for `t_order`
-- ----------------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
	`ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
	`AID`int(11) NOT NULL COMMENT '订单表关联账户表的外键',
	`ORDER_NUM` varchar(100) NOT NULL DEFAULT '0123456789' COMMENT '订单编号',
	`CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单创建时间',
	`END_TIME` timestamp  NOT NULL DEFAULT '1970-07-01 00:00:00' COMMENT '订单结束时间',
	`PAY_AMOUNT` varchar(50) NOT NULL DEFAULT '0' COMMENT '订单金额',
	`PAY_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '订单是否支付完成状态位:0表示未支付;1表示支付完成',
	`POWER_BANK_BORROW_PLACE` varchar(300) NOT NULL DEFAULT '东莞' COMMENT '借充电宝的地点，即订单创建地点',
	`POWER_BANK_BACK_PLACE` varchar(300) DEFAULT NULL COMMENT '归还电宝的地点，即订单完成(即订单结束)地点，但此时并不一定支付了订单',
	`POWER_BANK_STATUS` varchar(8) DEFAULT NULL COMMENT '订单中的充电宝的状态：0：租借中 1：归还成功',
	`POWER_BANK_ID` varchar(200) NOT NULL DEFAULT 'qdpb001' COMMENT '充电宝ID 默认qdpb001',
	`BOX_CHARGING_ID` varchar(200) NOT NULL DEFAULT 'qdcb001' COMMENT '充电箱ID 默认qdcb001',
	`PAY_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '订单支付完成时间,标志着订单状态位此时变为1',
   KEY `AID`(`AID`),
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 在 t_order 中为 aid 列添加名为 account_order 的外键，
-- 且此外键的参照为 t_account 表的 id 列，关联的操作为删除和更新
alter table `t_order` 
add constraint `account_order` foreign key (`aid`) 
references `t_account`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_water_bills`
-- --------------------------------------
DROP TABLE IF EXISTS `t_water_bills`;
CREATE TABLE `t_water_bills` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `OID` int(11) NOT NULL COMMENT '流水表关联订单表的外键',
  `AMOUNT` varchar(200) NOT NULL DEFAULT '0' COMMENT '流水金额',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '流水产生时间',
  `TYPE` varchar(8) DEFAULT NULL COMMENT '流水类型：0表示扣费；1表示充值；3：退还押金',
   KEY `OID`(`OID`),
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水表';

-- 在 t_water_bills 中为 oid 列添加名为 account_order 的外键，
-- 且此外键的参照为 t_order 表的 id 列，关联的操作为删除和更新
alter table `t_water_bills` 
add constraint `order_water_bills` foreign key (`oid`) 
references `t_order`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_charging_box`
-- --------------------------------------
DROP TABLE IF EXISTS `t_charging_box`;
CREATE TABLE `t_charging_box` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '充电箱id',
  `BOX_CUSTOMER_ID` varchar(50) NOT NULL COMMENT '充电箱Customer ID，如0x4D',
  `BOX_DATE` varchar(30) NOT NULL COMMENT '充电箱出厂时间，如1710',
  `BOX_SN` varchar(100) NOT NULL COMMENT '充电箱SN',
  `LOCATION` varchar(300) DEFAULT NULL COMMENT '充电箱摆放位置',
  `STATUS` varchar(100) DEFAULT NULL COMMENT '充电箱状态：0：离线 1：正常运行 2：故障',
  `MODEL` varchar(100) NOT NULL COMMENT '充电箱型号',
  `SOFTWARE_VERSION` varchar(100) NOT NULL COMMENT '充电箱软件版本号',
  `HEARTBREAK_TIME` varchar(20) DEFAULT NULL COMMENT '充电箱心跳时间',
  `BUSINESS` varchar(50) NOT NULL COMMENT '充电箱所处位置的业态，比如商场，酒吧等',
  `PRICING_TYPE` varchar(100) NOT NULL COMMENT '充电箱的消费类型，即定价策略',
  `BORROW_NUM` varchar(20) NOT NULL COMMENT '充电箱里充电宝已借出多少台',
  `POWER_BANK_NUM` varchar(20) NOT NULL COMMENT '充电箱总共有多少台充电宝，这个是一开始放进去的数量',
  `CAN_BORROW_NUM` varchar(20) NOT NULL COMMENT '充电箱里充电宝还有多少台可借',
  `CAN_BACK_NUM` varchar(20) NOT NULL COMMENT '可还到充电箱的充电宝数量',
  `MALFUNCTION_NUM` varchar(20) NOT NULL COMMENT '充电箱里故障充电宝数量',
  `SPEAKER_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '充电箱SIM卡CCID',
  `SIM_CCID` varchar(100) NOT NULL COMMENT '充电箱SIM卡CCID',
  `GIVEN_AGENT_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '充电箱给到代理商时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电箱表';


-- --------------------------------------
--  Table structure for `t_power_bank`
-- --------------------------------------
DROP TABLE IF EXISTS `t_power_bank`;
CREATE TABLE `t_power_bank` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '充电宝id',
  `CBID` int(11) NOT NULL COMMENT '充电宝表关联充电箱的外键',
  `LOCK_ID` varchar(50) NOT NULL COMMENT '充电宝锁位ID',
  `LOCK_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '充电宝锁位状态：0关闭，1开启',
  `LOCK_OPERATION_STATUS` varchar(8) NOT NULL DEFAULT '0' COMMENT '充电宝锁位操作状态：0操作失败，1操作成功',
  `PB_CUSTOMER_ID` varchar(50) NOT NULL COMMENT '充电宝Customer ID',
  `PB_MODEL` varchar(100) DEFAULT NULL COMMENT '充电宝型号',
  `PB_DATE` varchar(30) DEFAULT NULL COMMENT '充电宝出厂日期',
  `PB_SN` varchar(100) DEFAULT NULL COMMENT '充电宝SN',
  `PB_CAPACITY` varchar(20) DEFAULT NULL COMMENT '充电宝电量：0x1(0-19%) 0x2(20%-39%) 0x3(40%-59%) 0x4(60%-79%) 0x5(80%-99%) 0x6(100%',
  `PB_STATUS` varchar(20) DEFAULT NULL COMMENT '充电宝状态：0借出，1在位',
  `PB_PRIORITY` varchar(8) DEFAULT NULL COMMENT '充电宝电量是否大于80%，如果大于可优先借出，0否，1是',
  `BORROW_TIMES` varchar(50) NOT NULL DEFAULT '0' COMMENT '充电宝被借出次数',
  `TOTLA_USE_TIME` varchar(50) NOT NULL DEFAULT '0' COMMENT '充电宝总的使用时间,单位分钟',
   KEY `CBID`(`CBID`),
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电宝表';

-- 在 t_power_bank 中为 cbid 列添加名为 chargingBox_powerBank 的外键，
-- 且此外键的参照为 t_charging_box 表的 id 列，关联的操作为删除和更新
alter table `t_power_bank` 
add constraint `chargingBox_powerBank` foreign key (`cbid`) 
references `t_charging_box`(`id`) on delete cascade on update cascade;


-- --------------------------------------
--  Table structure for `t_agent`
-- --------------------------------------
DROP TABLE IF EXISTS `t_agent`;
CREATE TABLE `t_agent` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '代理商id',
  `REAL_NAME` varchar(100) NOT NULL COMMENT '代理商真实姓名',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '代理商用户名',
  `ID_NUMBER` varchar(100) NOT NULL COMMENT '代理商身份证号',
  `LEVEL` varchar(50) NOT NULL DEFAULT '青铜' COMMENT '代理商级别:初级：青铜合伙人 中级：白银 高级：黄金  超级：钻石',
  `SHARGING_RATIO` varchar(30) NOT NULL DEFAULT '0%' COMMENT '代理商分成比例',
  `SERVICE_CHARGES` varchar(100) DEFAULT '0' COMMENT '代理商服务费',
  `CAN_WITHDRAW` varchar(8) DEFAULT '0' COMMENT '代理商提现: 0:不可提现 1：可提现',
  `WITHDRAW_AMOUNT` varchar(100) DEFAULT '0' COMMENT '代理商提现金额：只有当代理商可以提现的时候才有提现金额',
  `ORDER_NUM` varchar(100) DEFAULT '0' COMMENT '代理商总的订单数',
  `TOTAL_REVENUE` varchar(100) DEFAULT '0' COMMENT '代理商总收益',
  `TOTAL_SHARE` varchar(100) DEFAULT '0' COMMENT '代理商总分成',
  `CHARGING_BOX_NUM` varchar(50) DEFAULT '0' COMMENT '代理商拥有的充电箱数量',
  `FROZEN_AMOUNT` varchar(100) DEFAULT '0' COMMENT '代理商冻结金额',
  `JOIN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成为代理商时间',
  `LEAVE_TIME` timestamp NOT NULL DEFAULT '1970-07-01 00:00:00' COMMENT '解除代理商身份时间',
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商表';


-- --------------------------------------
--  Table structure for `t_merchant`
-- --------------------------------------
DROP TABLE IF EXISTS `t_merchant`;
CREATE TABLE `t_merchant` (
  `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `AGID` int(11) NOT NULL COMMENT '商户表关联代理商表的外键',
  `LOGO` varchar(100) DEFAULT NULL COMMENT '商户logo',
  `NAME` varchar(100) DEFAULT NULL  COMMENT '商户名',
  `CITY` varchar(30) DEFAULT NULL COMMENT '商户所在城市',
  `STAR_LEVEL` varchar(30) DEFAULT NULL COMMENT '星级',
  `BUSINESS` varchar(50) DEFAULT NULL COMMENT '业态，比如车站等',
  `CONTRACT_PERSON_NAME` varchar(50) NOT NULL COMMENT '联系人姓名',
  `CONTRACT_PERSON_PHONE` varchar(50) NOT NULL COMMENT '联系人电话',
  `ID_NUMBER` varchar(100) NOT NULL COMMENT '联系人身份证号',
  `JOIN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成为商户商时间',
  `LEAVE_TIME` timestamp NOT NULL  DEFAULT '1970-07-01 00:00:00' COMMENT '解除商户身份时间',
   KEY `AGID`(`AGID`),
   PRIMARY KEY (`ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户表';

-- 在 t_merchant 中为 agid 列添加名为 agent_merchant 的外键，
-- 且此外键的参照为 t_agent 表的 id 列，关联的操作为删除和更新
alter table `t_merchant` 
add constraint `agent_merchant` foreign key (`agid`) 
references `t_agent`(`id`) on delete cascade on update cascade;


-- 开启外键约束
SET FOREIGN_KEY_CHECKS = 1   

