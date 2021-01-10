/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `mall`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cost` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`product_id`),
  KEY `userId` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`product_id`,`quantity`,`cost`,`user_id`,`create_time`,`update_time`) values (167,740,1,158,30,'2021-01-09 14:21:13','2021-01-09 14:21:13');

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int NOT NULL COMMENT '订单主键',
  `product_id` int NOT NULL COMMENT '商品主键',
  `quantity` int NOT NULL COMMENT '数量',
  `cost` float NOT NULL COMMENT '消费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___66E1BD8E2F10007B` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Data for the table `order_detail` */

insert  into `order_detail`(`id`,`order_id`,`product_id`,`quantity`,`cost`) values (86,109,733,2,304),(87,109,751,2,1178),(88,110,733,1,152),(89,111,733,1,152),(90,112,733,1,152),(91,113,733,1,152),(92,114,733,1,152),(93,115,733,1,152),(94,116,734,1,152),(95,116,745,3,1767),(96,116,768,1,5896),(97,117,742,4,632),(98,119,733,1,152),(99,119,733,1,152),(100,119,733,2,157),(101,120,752,1,589);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户主键',
  `login_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户地址',
  `cost` float DEFAULT NULL COMMENT '总金额',
  `serialnumber` varchar(255) DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`user_id`,`login_name`,`user_address`,`cost`,`serialnumber`,`create_time`,`update_time`) values (118,27,'zs','张三家',755,'sadfsadf','2021-01-09 22:36:43','2021-01-09 22:36:43'),(119,27,'zs','西昌学院',461,'DBD64ECE5F97AD9CE72F3C2CA78EF788','2021-01-09 22:38:22','2021-01-09 22:38:22'),(120,27,'zs','西昌学院',589,'910963B63140C4FCB29298E87F02D4E3','2021-01-10 10:25:45','2021-01-10 10:25:45');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `price` float NOT NULL COMMENT '价格',
  `stock` int NOT NULL COMMENT '库存',
  `category_id` int DEFAULT NULL,
  `file_name` varchar(200) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___94F6E55132E0915F` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=777 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`description`,`price`,`stock`,`category_id`,`file_name`) values (733,'香奈儿','订单',152,10,548,'baby_1.jpg'),(734,'洗面奶','',152,1,548,'baby_2.jpg'),(735,'水啫喱水','',152,998,548,'baby_3.jpg'),(736,'香水','',152,1000,548,'baby_4.jpg'),(737,'香水','',152,111,548,'baby_5.jpg'),(738,'润肤露','',45,109,548,'baby_6.jpg'),(739,'洁面装','',156,99,548,'bk_2.jpg'),(740,'电饭锅','',158,86,628,'bk_1.jpg'),(741,'婴儿喂奶装','',569,100,632,'bk_3.jpg'),(742,'坚果套餐','',158,1000,660,'bk_4.jpg'),(743,'超甜蜜崭','',589,1000,660,'bk_5.jpg'),(744,'华为2566','',589,1000,670,'de1.jpg'),(745,'荣耀3C','',589,100,670,'de2.jpg'),(746,'小米手环','',963,100,670,'de3.jpg'),(747,'华为2265','',896,1000,670,'de4.jpg'),(748,'越南坚果','',520,1,660,'de5.jpg'),(749,'日本进口马桶','',5866,100,628,'food_1.jpg'),(750,'联想Y系列','',569,1000,670,'food_2.jpg'),(751,'脑白金1号','',589,1000,676,'food_3.jpg'),(752,'莫里斯按','',589,999,676,'food_4.jpg'),(753,'三鹿好奶粉','',859,100,676,'food_5.jpg'),(754,'儿童牛奶','',5896,100,676,'food_6.jpg'),(755,'软沙发','',8596,99,628,'food_b1.jpg'),(756,'收纳盒','',5966,100,628,'food_b2.jpg'),(757,'洗衣液','',58,1000,628,'food_r.jpg'),(758,'红短沙发','',596,123,628,'fre_1.jpg'),(759,'新西兰奶粉','',5896,100,676,'fre_2.jpg'),(760,'婴儿车','',11000,97,681,'fre_3.jpg'),(761,'夏款婴儿车','',963,100,681,'fre_4.jpg'),(762,'抗压旅行箱','',569,1000,681,'fre_5.jpg'),(763,'透明手提箱','',8596,1000,681,'fre_6.jpg'),(764,'婴儿果粉','',5896,1000,660,'milk_1.jpg'),(765,'椰子粉','',5963,1000,660,'milk_2.jpg'),(766,'坚果蛋糕','',200,1,660,'milk_3.jpg'),(767,'编制手提箱','',5896,1000,681,'milk_4.jpg'),(768,'纸箱','',5896,3,681,'milk_5.jpg'),(769,'健胃液','',152,1000,676,'milk_6.jpg'),(770,'联想NTC','',8596,100,670,'milk_7.jpg'),(771,'香水1',NULL,100,100,548,'milk_8.jpg'),(772,'香水2',NULL,100,100,548,'pro1.jpg'),(773,'香水3',NULL,100,100,548,'pro2.jpg'),(774,'香水4',NULL,100,100,548,'pro3.jpg'),(775,'香水5',NULL,100,100,548,'pro4.jpg'),(776,'香水6',NULL,1,1,548,'pro5.jpg');

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___9EC2A4E236B12243` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=697 DEFAULT CHARSET=utf8;

/*Data for the table `product_category` */

insert  into `product_category`(`id`,`name`) values (548,'化妆品'),(628,'家用商品'),(654,'面部护理'),(655,'少女派'),(656,'餐具'),(657,'卫具'),(658,'叉子'),(659,'锅'),(660,'进口食品'),(661,'零食/糖果/巧克力'),(662,'坚果'),(663,'蜜饯'),(669,'孕期教育'),(670,'电子商品'),(671,'手机'),(672,'华为手机'),(673,'联想手机'),(674,'手环'),(675,'小米手环'),(676,'保健食品'),(677,'老年保健品'),(678,'中年营养品'),(679,'儿童保健品'),(680,'脑白金'),(681,'箱包'),(682,'旅行箱'),(683,'手提箱'),(684,'大型'),(685,'小型'),(686,'中型'),(687,'大型'),(688,'中型'),(689,'小型'),(690,'电脑'),(691,'联想电脑'),(692,'刀叉'),(693,'碗筷'),(696,'客厅专用');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `gender` int NOT NULL DEFAULT '1' COMMENT '性别(1:男 0：女)',
  `identity_code` varchar(60) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `file_name` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___C96109CC3A81B327` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`login_name`,`user_name`,`password`,`gender`,`identity_code`,`email`,`mobile`,`file_name`,`create_time`,`update_time`) values (2,'admin','系统管理员','123123',0,'130406198302141869','hello11@bdqn.com','1583233515','7.jpg','2020-05-18 06:22:27','2020-05-18 06:22:32'),(10,'cgn','程广宁','123',1,'140225189987854589','1044732267@qq.com','13366055011','2.jpg','2020-05-18 06:22:34','2020-05-18 06:22:37'),(27,'zs','张三','123456',1,'140225189987854589','zhangsan@qq.com','13366055011','27.jpg','2020-12-27 07:07:44','2021-01-10 17:25:50'),(30,'ls','李四','123456',1,'140225189987854589','lisi@qq.com','13366055011','30.jpg','2021-01-09 14:06:50','2021-01-10 17:51:04'),(31,'ww','王五','123456',1,'140225189987854589','lisi@qq.com','13366055011','31.jpg','2021-01-09 14:18:33','2021-01-10 17:53:53');

/*Table structure for table `user_address` */

DROP TABLE IF EXISTS `user_address`;

CREATE TABLE `user_address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int DEFAULT NULL COMMENT '用户主键',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `isdefault` int DEFAULT '0' COMMENT '是否是默认地址（1:是 0否）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `user_address` */

insert  into `user_address`(`id`,`user_id`,`address`,`remark`,`isdefault`,`create_time`,`update_time`) values (48,27,'西昌学院','学校',0,'2021-01-09 15:09:19','2021-01-09 21:02:37'),(49,30,'天赋广场','广场',0,'2021-01-09 21:02:37','2021-01-09 21:18:40'),(50,30,'西昌学院','学校',1,'2021-01-09 21:18:40','2021-01-09 21:18:40');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
