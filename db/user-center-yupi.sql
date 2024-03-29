/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.31-log : Database - yupi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yupi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `yupi`;

/*Table structure for table `qc_random_unqualifiedticket_department` */

DROP TABLE IF EXISTS `qc_random_unqualifiedticket_department`;

CREATE TABLE `qc_random_unqualifiedticket_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `random_unqualified_ticket_id` int(11) DEFAULT NULL,
  `level` smallint(6) DEFAULT NULL,
  `responsible_department_id` int(11) DEFAULT NULL,
  `attr` varchar(255) DEFAULT NULL COMMENT '用来保存 责任部门的扩展信息，比如 商务责任时，保存 供应商信息，制造责任，选择“其他”时，保存页面输入值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='随机不合格票责任部门关联表，两者是多对多的关系，一个 随机不合格票 可以对应 多个 责任部门；任意一个 责任部门 可以对';

/*Data for the table `qc_random_unqualifiedticket_department` */

insert  into `qc_random_unqualifiedticket_department`(`id`,`random_unqualified_ticket_id`,`level`,`responsible_department_id`,`attr`) values 
(1,1,1,2022092311,NULL),
(2,1,2,2022092315,NULL),
(3,1,2,2022092316,NULL),
(4,1,1,2022092312,NULL),
(5,1,2,2022092322,NULL),
(6,1,1,2022092313,NULL),
(7,1,2,2022092325,NULL),
(8,1,1,2022092314,NULL),
(9,1,2,2022092327,NULL),
(10,1,3,2022092331,NULL),
(11,1,2,2022092328,NULL),
(12,1,3,2022092334,NULL),
(13,1,2,2022092329,NULL),
(14,1,3,2022092338,NULL),
(15,1,2,2022092330,NULL),
(16,1,3,2022092344,NULL);

/*Table structure for table `qc_responsible_department` */

DROP TABLE IF EXISTS `qc_responsible_department`;

CREATE TABLE `qc_responsible_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `responsible_department_name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `level` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2022092346 DEFAULT CHARSET=utf8mb4 COMMENT='责任部门';

/*Data for the table `qc_responsible_department` */

insert  into `qc_responsible_department`(`id`,`responsible_department_name`,`parent_id`,`level`) values 
(2022092311,'制造',-1,1),
(2022092312,'商务',-1,1),
(2022092313,'工艺',-1,1),
(2022092314,'研发',-1,1),
(2022092315,'下料',2022092311,2),
(2022092316,'机加',2022092311,2),
(2022092317,'焊接',2022092311,2),
(2022092318,'涂装',2022092311,2),
(2022092319,'装配',2022092311,2),
(2022092320,'调试',2022092311,2),
(2022092321,'其他',2022092311,2),
(2022092322,'外购',2022092312,2),
(2022092323,'整体外协',2022092312,2),
(2022092324,'工序外协',2022092312,2),
(2022092325,'技术工艺所',2022092313,2),
(2022092326,'产线规划所',2022092313,2),
(2022092327,'一院',2022092314,2),
(2022092328,'二院',2022092314,2),
(2022092329,'三院',2022092314,2),
(2022092330,'四院',2022092314,2),
(2022092331,'搅拌机所',2022092327,3),
(2022092332,'涂布机所',2022092327,3),
(2022092333,'辊压分切机所',2022092327,3),
(2022092334,'卷绕机所',2022092328,3),
(2022092335,'叠片机所',2022092328,3),
(2022092336,'注液机所',2022092328,3),
(2022092337,'中段组装线所',2022092328,3),
(2022092338,'模组pack线所',2022092329,3),
(2022092339,'化成分容所',2022092329,3),
(2022092340,'立库物流所',2022092329,3),
(2022092341,'前段控制所',2022092330,3),
(2022092342,'中段控制所',2022092330,3),
(2022092343,'后段控制所',2022092330,3),
(2022092344,'软件所',2022092330,3),
(2022092345,'电气标准化所',2022092330,3);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `dict_code` varchar(25) DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(50) DEFAULT NULL COMMENT '字典名称-展示用',
  `dict_class` varchar(50) DEFAULT NULL COMMENT '字典分类',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除(0-未删除 1-已删除)',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(50) DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `modify_name` varchar(50) DEFAULT NULL COMMENT '修改人name',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典表';

/*Data for the table `sys_dict` */

/*Table structure for table `sys_dict_item` */

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典项id',
  `dict_id` int(11) DEFAULT NULL COMMENT '字典主键id',
  `item_code` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '字典项编码',
  `item_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '字典项名称',
  `item_value` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '字典项值',
  `expand1` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展字段1',
  `expand2` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展字段2',
  `expand3` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展字段3',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '逻辑删除(0-未删除 1-已删除)',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `modify_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人name',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统字典项';

/*Data for the table `sys_dict_item` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` varchar(30) DEFAULT NULL COMMENT '登录账号',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `user_type` varchar(2) DEFAULT '0' COMMENT '用户类型0-系统，1-外部',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '性别0-男 1-女',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '账号状态0-正常 1-停用',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志0-未删除 1-已删除',
  `create_name` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_name` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

/*Data for the table `sys_user` */

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tag_name` varchar(256) DEFAULT NULL COMMENT '标签名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `is_parent` tinyint(4) DEFAULT NULL COMMENT '是否是父标签(0-否，1-是)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父标签id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除(0-否 1-是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='标签表';

/*Data for the table `tag` */

insert  into `tag`(`id`,`tag_name`,`user_id`,`is_parent`,`parent_id`,`create_time`,`update_time`,`is_delete`) values 
(1,'Java',1,NULL,NULL,'2022-12-06 14:50:01','2022-12-06 14:50:01',0),
(2,'Python',2,NULL,NULL,'2022-12-06 19:36:39','2022-12-06 19:36:39',0),
(4,'java',3,0,999,'2023-04-25 19:22:25','2023-04-25 19:22:25',0);

/*Table structure for table `ticket` */

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ticket_code` varchar(30) DEFAULT NULL COMMENT '票编号',
  `ticket_level` tinyint(3) DEFAULT NULL COMMENT '票等级',
  `dlt` tinyint(3) DEFAULT '0' COMMENT '逻辑删除标志',
  `handle_way` tinyint(3) DEFAULT NULL COMMENT '票处理方式',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(30) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `modify_name` varchar(30) DEFAULT NULL COMMENT '修改人姓名',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `source` varchar(30) DEFAULT NULL COMMENT '票的来源',
  `price` double(10,2) DEFAULT NULL COMMENT '票的价格(小数点后两位)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COMMENT='不合格票表';

/*Data for the table `ticket` */

insert  into `ticket`(`id`,`ticket_code`,`ticket_level`,`dlt`,`handle_way`,`user_id`,`create_id`,`create_name`,`create_time`,`modify_id`,`modify_name`,`modify_time`,`source`,`price`) values 
(1,'220826666',0,0,4,3,1,'刘德意','2022-08-26 15:24:51',NULL,NULL,'2022-08-26 15:24:51','自己造的数据',78.90),
(2,'220826777',9,0,9,1,1,'刘德意','2022-08-26 15:27:48',NULL,NULL,'2022-08-26 15:27:48','自己造的数据',88.80),
(3,'20220923145156',1,0,2,0,-5728551419571951686,'偶数','2022-09-23 14:51:56',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(4,'20220923145156',2,0,2,0,3332630002577662175,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(5,'20220923145156',2,0,4,0,5288796423701692772,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(6,'20220923145156',9,0,3,0,-2436934890153631456,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(7,'20220923145156',9,0,1,0,-1353341921090764961,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(8,'20220923145156',8,0,2,0,4251702769176845797,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(9,'20220923145156',2,0,1,0,653614175642847283,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(10,'20220923145156',3,0,4,0,-7766592898459219192,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(11,'20220923145156',2,0,3,0,792463274743488554,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(12,'20220923145156',3,0,2,0,6849730434053608037,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(13,'20220923145156',7,0,2,0,1160587397508079060,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(14,'20220923145156',6,0,3,0,5878379197759688798,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(15,'20220923145156',2,0,4,0,-9099298056394166653,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(16,'20220923145156',6,0,1,0,3670267408621690968,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(17,'20220923145156',9,0,3,0,4345166333237816015,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(18,'20220923145156',5,0,1,0,-5959710393529796292,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(19,'20220923145156',5,0,3,0,-5182083174176343276,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(20,'20220923145156',5,0,3,0,-2384561117318112777,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(21,'20220923145156',1,0,1,0,-1102165920393278481,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(22,'20220923145156',5,0,0,0,-4520774285220788742,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(23,'20220923145156',4,0,4,0,8520180051365024453,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(24,'20220923145156',5,0,4,0,-4731542805349412422,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(25,'20220923145156',8,0,1,0,2841630804449288764,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(26,'20220923145156',6,0,4,0,-764533980856058063,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(27,'20220923145156',4,0,0,0,-7922909025183871296,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(28,'20220923145156',4,0,3,0,-4479990345215497637,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(29,'20220923145156',9,0,4,0,-2624088679003249921,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(30,'20220923145156',1,0,1,0,1154950713644254650,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(31,'20220923145156',1,0,1,0,7052044436030202990,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(32,'20220923145156',1,0,0,0,-1353381800960104749,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(33,'20220923145156',1,0,4,0,-563399381250218047,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(34,'20220923145156',7,0,2,0,-8592778939051597137,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(35,'20220923145156',1,0,1,0,-4398257855599446666,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(36,'20220923145156',2,0,1,0,-8186219110031754661,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(37,'20220923145156',5,0,4,0,4126064117398568523,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(38,'20220923145156',5,0,4,0,6518549341084981096,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(39,'20220923145156',3,0,3,0,-4578308858810490940,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(40,'20220923145156',3,0,3,0,715599850877982827,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(41,'20220923145156',2,0,2,0,-6054002920731821913,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(42,'20220923145156',7,0,0,0,-3326958428280148610,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(43,'20220923145156',9,0,3,0,2876128815910997375,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(44,'20220923145156',2,0,2,0,6526381003805672637,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(45,'20220923145156',7,0,2,0,-4982505171936871140,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(46,'20220923145156',6,0,0,0,1515944225944553163,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(47,'20220923145156',0,0,2,0,-7465414661907380976,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(48,'20220923145156',6,0,1,0,-6880021001301236918,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(49,'20220923145156',7,0,2,0,-936545222199814604,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(50,'20220923145156',0,0,0,0,7053683610901835110,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(51,'20220923145156',2,0,0,0,-2793574253329401567,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00),
(52,'20220923145156',8,0,4,0,6742720947999426635,'偶数','2022-09-23 14:51:57',NULL,NULL,'2022-09-23 14:51:56',NULL,0.00);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id(主键)',
  `username` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `user_account` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '登录账号',
  `avatar_url` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `user_password` varchar(512) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `phone` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `email` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `user_status` int(11) DEFAULT '0' COMMENT '用户状态 0-正常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除(逻辑删除0-不删除)',
  `user_role` int(11) NOT NULL DEFAULT '0' COMMENT '用户角色0-普通角色，1-管理员',
  `tags` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '标签列表',
  `profile` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '个人简介',
  `planet_code` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '星球编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`user_account`,`avatar_url`,`gender`,`user_password`,`phone`,`email`,`user_status`,`create_time`,`update_time`,`is_delete`,`user_role`,`tags`,`profile`,`planet_code`) values 
(1,'刘德意','liudy23','https://api2.mubu.com/v3/photo/925eae67-7595-426d-a629-16ff249aaf75.jpg',0,'123','18811553417','18811553417@163.com',0,'2022-05-01 14:57:01','2022-07-11 12:10:01',0,0,'Java,Vue3,JavaScript','liudy23 is so handsome','9999'),
(2,'苏谣','ldy23',NULL,1,'7844755df1f78b3f3f12096c6df974fc','18331500978','188@163.com',0,'2022-05-03 10:09:54','2022-08-12 10:09:54',0,1,'English',NULL,NULL),
(3,'慕紫','suyao',NULL,1,'7844755df1f78b3f3f12096c6df974fc','18811553417','1155@163.com',0,'2022-05-03 10:45:40','2022-05-03 10:45:40',0,0,NULL,NULL,NULL),
(4,'赤焰','admin',NULL,0,'3b711c0292e8b9c3b4d483e76c9ede18','18331500978','3417@163.com',0,'2022-05-03 13:46:42','2022-05-03 13:46:42',0,0,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
