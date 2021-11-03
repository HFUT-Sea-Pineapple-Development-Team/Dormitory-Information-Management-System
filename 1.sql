-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.26 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 dorm 的数据库结构
CREATE DATABASE IF NOT EXISTS `dorm` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dorm`;

-- 导出  表 dorm.dormbuild 结构
CREATE TABLE IF NOT EXISTS `dormbuild` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dormBuildName` int NOT NULL DEFAULT '0' COMMENT '宿舍楼号',
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宿舍楼简介',
  `sex` int NOT NULL DEFAULT '0' COMMENT '宿舍性别类型(0女 1男)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dormBuildName` (`dormBuildName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍楼表';

-- 正在导出表  dorm.dormbuild 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `dormbuild` DISABLE KEYS */;
REPLACE INTO `dormbuild` (`id`, `dormBuildName`, `detail`, `sex`) VALUES
	(1, 5, '我', 1),
	(4, 4, '发送到', 1),
	(5, 9, '雨哥的宿舍', 1),
	(6, 8, '女生寝室', 1),
	(7, 100, '阿萨德', 1),
	(8, 10, '打啥', 1),
	(9, 7, '快乐的地方', 1),
	(10, 401, '游乐场', 1);
/*!40000 ALTER TABLE `dormbuild` ENABLE KEYS */;

-- 导出  表 dorm.dormmanagerassociation 结构
CREATE TABLE IF NOT EXISTS `dormmanagerassociation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关联主键',
  `stu_code` int NOT NULL COMMENT '学工号',
  `dormBuildName` int NOT NULL COMMENT '宿舍楼号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍宿管关联表';

-- 正在导出表  dorm.dormmanagerassociation 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `dormmanagerassociation` DISABLE KEYS */;
/*!40000 ALTER TABLE `dormmanagerassociation` ENABLE KEYS */;

-- 导出  表 dorm.hygiene_info 结构
CREATE TABLE IF NOT EXISTS `hygiene_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `build_id` int DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int DEFAULT NULL COMMENT '寝室房间号',
  `grade_18` float DEFAULT NULL COMMENT '18年寝室卫生打分',
  `grade_19` float DEFAULT NULL COMMENT '19年寝室卫生打分',
  `grade_20` float DEFAULT NULL COMMENT '20年寝室卫生打分',
  `grade_21` float DEFAULT NULL COMMENT '21年寝室卫生打分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.hygiene_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `hygiene_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `hygiene_info` ENABLE KEYS */;

-- 导出  表 dorm.notice 结构
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '通知公告主键',
  `time` datetime NOT NULL COMMENT '公告发布时间',
  `detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '公告内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告';

-- 正在导出表  dorm.notice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 导出  表 dorm.repair_info 结构
CREATE TABLE IF NOT EXISTS `repair_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学工号，工作人员和学生统一',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `build_id` int DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int DEFAULT NULL COMMENT '寝室房间号',
  `report_time` datetime DEFAULT NULL COMMENT '报修时间',
  `repair_time` datetime DEFAULT NULL COMMENT '维修时间',
  `is_repair` tinyint(1) DEFAULT NULL COMMENT '是否维修成功；0：已维修成功；1：未维修成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.repair_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `repair_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair_info` ENABLE KEYS */;

-- 导出  表 dorm.room_info 结构
CREATE TABLE IF NOT EXISTS `room_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int DEFAULT NULL COMMENT '寝室房间号',
  `person_num` int DEFAULT NULL COMMENT '寝室人数',
  `remain_water_charge` float DEFAULT NULL COMMENT '剩余水费',
  `remain_elec_charge` float DEFAULT NULL COMMENT '剩余电费',
  `is_good` tinyint(1) NOT NULL COMMENT '是否优秀寝室 0:是,1:否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.room_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;

-- 导出  表 dorm.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `roleId` int NOT NULL DEFAULT '2' COMMENT '用户角色(0超级管理员 1宿舍管理员 2学生)',
  `stu_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `sex` int NOT NULL COMMENT '性别(0代表女 1代表男)',
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业',
  `dormBuildName` int DEFAULT NULL COMMENT '宿舍楼号',
  `roomId` int DEFAULT NULL COMMENT '寝室号',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `class` int DEFAULT NULL COMMENT '班级号',
  `leave` int NOT NULL DEFAULT '0' COMMENT '是否离校(0在校 1离校)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_code` (`stu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- 正在导出表  dorm.user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `password`, `roleId`, `stu_code`, `sex`, `major`, `dormBuildName`, `roomId`, `tel`, `class`, `leave`) VALUES
	(1, 'admin', '123', 0, '110', 0, NULL, NULL, NULL, '17777777777', NULL, 0),
	(2, 'keeper1', '123', 1, '10000', 1, NULL, NULL, NULL, '17777777777', NULL, 0),
	(3, '李白', '123', 2, '2018217666', 1, NULL, NULL, NULL, '17777777777', NULL, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 dorm.visit_info 结构
CREATE TABLE IF NOT EXISTS `visit_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记姓名',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客联系号码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.visit_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `visit_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `visit_info` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
