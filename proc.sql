/*
 Navicat Premium Data Transfer

 Source Server         : lucky
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : dorm

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 08/11/2021 20:42:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Procedure structure for insertAll
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertAll`;
delimiter ;;
CREATE PROCEDURE `insertAll`(IN `code` varchar(20),IN `stu_Name` varchar(20),IN `stu_Sex` int(1),IN `stu_Tel` varchar(11),IN `stu_DormBuildName` int(2),IN `stu_RoomId` int(3),IN `stu_Major` varchar(20),IN `stu_Class` int(2))
BEGIN
	declare stu_password VARCHAR(20);
	set stu_password = `code`;
	INSERT INTO `dorm`.`user` (`name`, `password`,`stu_code`, `sex`, `major`, `dormBuildId`, `roomId`, `tel`, `class` ) VALUES (`stu_Name`,stu_password,`code`,`stu_Sex`,`stu_Major`,`stu_DormBuildName`,`stu_RoomId`,`stu_Tel`,`stu_Class`);

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
