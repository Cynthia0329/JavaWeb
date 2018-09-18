/*
SQLyog Ultimate v8.32 
MySQL - 5.5.56 : Database - musicweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`musicweb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `musicweb`;

/*Table structure for table `manage` */

DROP TABLE IF EXISTS `manage`;

CREATE TABLE `manage` (
  `id` varchar(64) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `account` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manage` */

insert  into `manage`(`id`,`name`,`account`,`password`) values ('1','admin','admin','202cb962ac59075b964b07152d234b70');

/*Table structure for table `webalbm` */

DROP TABLE IF EXISTS `webalbm`;

CREATE TABLE `webalbm` (
  `albm_id` varchar(64) NOT NULL,
  `albm_name` varchar(128) DEFAULT NULL,
  `singer_id` varchar(64) DEFAULT NULL,
  `albm_date` date DEFAULT NULL,
  `albm_jianjie` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`albm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `webalbm` */

insert  into `webalbm`(`albm_id`,`albm_name`,`singer_id`,`albm_date`,`albm_jianjie`) values ('29097fab-1087-46a4-82ee-d204c5d449aa','夜','58089e5a-87be-446f-b53b-86699c402640','2018-06-05','啊'),('8cc5c47f-3e5d-4224-8a6f-9276bd735ae3','GOOD NIGHT','40f8ed78-5e41-45e0-bde8-0eb2a85652df','2018-05-29','啊'),('f67fd134-ed11-4962-87fa-c727fd9b3079','七里香','bdef4d39-d418-4448-8ef2-275f545d1247','2008-06-03','周杰伦');

/*Table structure for table `webfile` */

DROP TABLE IF EXISTS `webfile`;

CREATE TABLE `webfile` (
  `fileid` varchar(64) NOT NULL,
  `filename` varchar(200) DEFAULT NULL,
  `filepath` varchar(200) DEFAULT NULL,
  `filesize` mediumtext,
  `fileuploaddate` date DEFAULT NULL,
  `file_pk_id` varchar(64) DEFAULT NULL,
  `file_pk_lx` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `webfile` */

insert  into `webfile`(`fileid`,`filename`,`filepath`,`filesize`,`fileuploaddate`,`file_pk_id`,`file_pk_lx`) values ('6c47eb03-23a7-450d-9aa3-07e0ecb8d8a7','QQ截图20180618000142.jpg','D:\\music\\076a5020-7920-4337-854c-0e2ce6cc9931.jpg','5117','2018-06-18','40f8ed78-5e41-45e0-bde8-0eb2a85652df',NULL),('89c0722a-3a89-4453-a6aa-5b55da55cc84','AKA.imp小鬼 - GOOD NIGHT.mp3','D:\\music\\169c884c-9f90-4b37-8d70-00a3718ed110.mp3','7118371','2018-06-18','24138382-3899-4cbb-b1be-1db6a8e50bf1',NULL),('fdabf2c3-5874-483b-9800-ab111d716138','黑呢 - 谁（Cover 廖俊涛）.mp3','D:\\music\\c81529e1-3d52-419a-9928-2d35b569b0d7.mp3','5701658','2018-06-18','d3ffebee-cc9c-4da4-be2c-25dd8777eda7',NULL);

/*Table structure for table `webmusic` */

DROP TABLE IF EXISTS `webmusic`;

CREATE TABLE `webmusic` (
  `music_id` varchar(64) NOT NULL,
  `music_albm_id` varchar(200) DEFAULT NULL,
  `music_shichang` int(11) DEFAULT NULL,
  `music_filepath` varchar(300) DEFAULT NULL,
  `music_geci` varchar(300) DEFAULT NULL,
  `music_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `webmusic` */

insert  into `webmusic`(`music_id`,`music_albm_id`,`music_shichang`,`music_filepath`,`music_geci`,`music_name`) values ('24138382-3899-4cbb-b1be-1db6a8e50bf1','f67fd134-ed11-4962-87fa-c727fd9b3079',NULL,NULL,NULL,'蒲公英的约定'),('d3ffebee-cc9c-4da4-be2c-25dd8777eda7','29097fab-1087-46a4-82ee-d204c5d449aa',NULL,NULL,NULL,'谁');

/*Table structure for table `websinger` */

DROP TABLE IF EXISTS `websinger`;

CREATE TABLE `websinger` (
  `singer_id` varchar(64) NOT NULL,
  `singer_name` varchar(64) DEFAULT NULL,
  `singer_birthdate` date DEFAULT NULL,
  `singer_shuxiang` varchar(200) DEFAULT NULL,
  `singer_xingzuo` varchar(100) DEFAULT NULL,
  `singer_aihao` varchar(200) DEFAULT NULL,
  `singer_jianjie` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`singer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `websinger` */

insert  into `websinger`(`singer_id`,`singer_name`,`singer_birthdate`,`singer_shuxiang`,`singer_xingzuo`,`singer_aihao`,`singer_jianjie`) values ('40f8ed78-5e41-45e0-bde8-0eb2a85652df','小鬼','2018-05-30','白羊','巨蟹','啊','啊'),('58089e5a-87be-446f-b53b-86699c402640','黑呢','2018-06-07','鼠','天蝎','啊','啊'),('bdef4d39-d418-4448-8ef2-275f545d1247','周杰伦','1979-06-05','鼠','巨蟹座','唱歌','流行创作歌手');

/*Table structure for table `webuser` */

DROP TABLE IF EXISTS `webuser`;

CREATE TABLE `webuser` (
  `userid` varchar(64) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `usersex` varchar(10) DEFAULT NULL,
  `usercard` varchar(64) DEFAULT NULL,
  `useraddress` varchar(200) DEFAULT NULL,
  `userbirthdate` date DEFAULT NULL,
  `userzt` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `webuser` */

insert  into `webuser`(`userid`,`username`,`password`,`usersex`,`usercard`,`useraddress`,`userbirthdate`,`userzt`) values ('3f195dd7-4fb7-4295-9553-c1d3122a477f','123','202cb962ac59075b964b07152d234b70','123','123','123','2018-06-07','1'),('8073a8dc-71d8-4570-980d-683cb05ac0d4','111111','202cb962ac59075b964b07152d234b70','v','513029199603291465','恩恩','2018-06-16','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
