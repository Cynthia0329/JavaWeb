/*
SQLyog Ultimate v8.32 
MySQL - 5.5.56 : Database - salegood
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`salegood` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `salegood`;

/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `id` varchar(100) NOT NULL,
  `goodbh` varchar(100) NOT NULL,
  `goodname` varchar(200) NOT NULL,
  `goodprice` double DEFAULT NULL,
  `goodcategoryid` varchar(100) DEFAULT NULL,
  `storeMin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `good` */

/*Table structure for table `goodcategory` */

DROP TABLE IF EXISTS `goodcategory`;

CREATE TABLE `goodcategory` (
  `id` varchar(100) NOT NULL,
  `categoryBh` varchar(50) NOT NULL,
  `categoryName` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodcategory` */

/*Table structure for table `goodguoqi` */

DROP TABLE IF EXISTS `goodguoqi`;

CREATE TABLE `goodguoqi` (
  `id` varchar(100) NOT NULL,
  `goodId` varchar(100) DEFAULT NULL,
  `goodNum` int(11) DEFAULT NULL,
  `goodJprice` double DEFAULT NULL,
  `goodGuoQiDate` date DEFAULT NULL,
  `sumMoney` double DEFAULT NULL,
  `storePh` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodguoqi` */

/*Table structure for table `goodstore` */

DROP TABLE IF EXISTS `goodstore`;

CREATE TABLE `goodstore` (
  `id` varchar(100) NOT NULL,
  `storePh` varchar(50) DEFAULT NULL,
  `goodId` varchar(50) DEFAULT NULL,
  `goodJPrice` float DEFAULT NULL,
  `storeNum` int(11) DEFAULT NULL,
  `goodValidDate` date DEFAULT NULL,
  `goodXs` double DEFAULT NULL,
  `storeDate` date DEFAULT NULL,
  `goodSumMoney` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodstore` */

/*Table structure for table `manage` */

DROP TABLE IF EXISTS `manage`;

CREATE TABLE `manage` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manage` */

insert  into `manage`(`id`,`name`,`account`,`password`) values ('1','admin','admin','c81e728d9d4c2f636f067f89cc14862c');

/*Table structure for table `otherproject` */

DROP TABLE IF EXISTS `otherproject`;

CREATE TABLE `otherproject` (
  `id` varchar(100) NOT NULL,
  `projectName` varchar(200) NOT NULL,
  `projectPrice` double DEFAULT NULL,
  `projectDate` date DEFAULT NULL,
  `projectRemark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `otherproject` */

/*Table structure for table `salerecord` */

DROP TABLE IF EXISTS `salerecord`;

CREATE TABLE `salerecord` (
  `id` varchar(100) NOT NULL,
  `saleDate` date DEFAULT NULL,
  `storePh` varchar(100) DEFAULT NULL,
  `saleNum` int(11) DEFAULT NULL,
  `salePrice` double DEFAULT NULL,
  `saleMoney` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salerecord` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
