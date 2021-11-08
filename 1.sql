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
  `dormBuildName` int NOT NULL DEFAULT '0' COMMENT '宿舍楼名称',
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宿舍楼简介',
  `sex` int NOT NULL DEFAULT '0' COMMENT '宿舍性别类型(0女 1男)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dormBuildName` (`dormBuildName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍楼表';

-- 正在导出表  dorm.dormbuild 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `dormbuild` DISABLE KEYS */;
REPLACE INTO `dormbuild` (`id`, `dormBuildName`, `detail`, `sex`) VALUES
	(1, 5, 'hello', 1),
	(4, 4, '发送到', 1),
	(5, 9, '雨哥的宿舍', 1),
	(6, 8, '女生寝室', 1),
	(7, 100, '阿萨德', 1),
	(8, 10, '打啥', 1),
	(9, 7, '快乐的地方', 1),
	(10, 401, '游乐场', 1);
/*!40000 ALTER TABLE `dormbuild` ENABLE KEYS */;

-- 导出  表 dorm.hygiene_info 结构
CREATE TABLE IF NOT EXISTS `hygiene_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `build_id` int DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int DEFAULT NULL COMMENT '寝室房间号',
  `grade_18` float DEFAULT NULL COMMENT '18年寝室卫生打分',
  `grade_19` float DEFAULT NULL COMMENT '19年寝室卫生打分',
  `grade_20` float DEFAULT NULL COMMENT '20年寝室卫生打分',
  `grade_21` float DEFAULT NULL COMMENT '21年寝室卫生打分',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `build_id` (`build_id`),
  CONSTRAINT `FK_hygiene_info_dormbuild` FOREIGN KEY (`build_id`) REFERENCES `dormbuild` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.hygiene_info 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `hygiene_info` DISABLE KEYS */;
REPLACE INTO `hygiene_info` (`id`, `build_id`, `room_id`, `grade_18`, `grade_19`, `grade_20`, `grade_21`) VALUES
	(1, 9, 406, 100, 90, 95, 90),
	(2, 9, 407, 80, 85, 90, 95),
	(3, 1, 101, 100, 100, 100, 100),
	(5, 9, 408, NULL, NULL, NULL, NULL),
	(8, 1, 410, NULL, NULL, NULL, NULL),
	(9, 1, 408, NULL, NULL, NULL, NULL),
	(10, 5, 325, NULL, NULL, NULL, NULL),
	(11, 9, 406, NULL, NULL, NULL, NULL),
	(12, 1, 600, NULL, NULL, NULL, NULL),
	(13, 1, 120, NULL, NULL, NULL, NULL),
	(14, 9, 214, NULL, NULL, NULL, NULL),
	(15, 1, 408, NULL, NULL, NULL, NULL),
	(16, 1, 112, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `hygiene_info` ENABLE KEYS */;

-- 导出  表 dorm.notice 结构
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公告内容',
  `receivers_num` int NOT NULL DEFAULT '0' COMMENT '收到通知人数',
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学工号',
  `time` date NOT NULL COMMENT '公告发布时间',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_icelandic_ci DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `stu_id` (`stu_id`) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `user` (`stu_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_icelandic_ci ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.notice 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
REPLACE INTO `notice` (`id`, `title`, `content`, `receivers_num`, `stu_id`, `time`, `tel`) VALUES
	(1, '疫情', '不准出校门', 6, '10000', '2021-11-05', '18856357393'),
	(3, '寒假时间', '寒假开始啦，同学们新年快乐', 6, '10000', '2021-11-06', '18856357393'),
	(4, '放假注意事项', '注意安全', 11, '10000', '2021-11-08', '18856357393'),
	(8, '降温', '多穿衣服', 12, '10000', '2021-11-06', '18856357393'),
	(9, NULL, NULL, 4, '10000', '2021-11-06', NULL);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 导出  表 dorm.repair_info 结构
CREATE TABLE IF NOT EXISTS `repair_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学工号，工作人员和学生统一',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `build_id` int NOT NULL COMMENT '宿舍楼号',
  `room_id` int NOT NULL COMMENT '寝室房间号',
  `report_time` date NOT NULL COMMENT '报修时间',
  `repair_time` date DEFAULT NULL COMMENT '维修时间',
  `idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修意见',
  `is_satisfied` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修是否满意：非常满意，满意，一般，不满意',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报修原因',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `is_finished` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修是否完成',
  `repair_report` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修完成报告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.repair_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `repair_info` DISABLE KEYS */;
REPLACE INTO `repair_info` (`id`, `stu_id`, `name`, `build_id`, `room_id`, `report_time`, `repair_time`, `idea`, `is_satisfied`, `reason`, `tel`, `is_finished`, `repair_report`) VALUES
	(21, '2018217666', '翁少勇', 9, 406, '2021-11-05', '2021-11-05', '速度很快', '非常满意', '大傻逼', '18856357393', '是', '已完成，注意安全'),
	(24, '2018217632', '蔡哲锐', 9, 403, '2021-11-05', NULL, NULL, NULL, '我不知道', '18856357393', NULL, NULL),
	(25, '2018217632', '姜天', 9, 403, '2021-11-05', NULL, NULL, NULL, '我也不知道', '18856357393', NULL, NULL),
	(27, '2018217632', '卢春雨', 9, 406, '2021-11-01', '2021-11-03', '从这些', '一般', '连不上校园网', '18856357393', '是', '已经修好');
/*!40000 ALTER TABLE `repair_info` ENABLE KEYS */;

-- 导出  表 dorm.room_info 结构
CREATE TABLE IF NOT EXISTS `room_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL COMMENT '寝室房间号',
  `build_num` int NOT NULL COMMENT '楼号',
  `person_num` int NOT NULL DEFAULT '0' COMMENT '寝室人数',
  `remain_water_charge` float NOT NULL DEFAULT '0' COMMENT '剩余水费',
  `remain_elec_charge` float NOT NULL DEFAULT '0' COMMENT '剩余电费',
  `is_good` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否优秀寝室 0:是,1:否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.room_info 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
REPLACE INTO `room_info` (`id`, `room_id`, `build_num`, `person_num`, `remain_water_charge`, `remain_elec_charge`, `is_good`) VALUES
	(1, 101, 1, 1, 30, 50, 1),
	(2, 406, 5, 0, 30, 60, 0),
	(3, 408, 5, 0, 100, 100, 0),
	(6, 325, 5, 0, 0, 0, 0),
	(7, 406, 9, 3, 0, 0, 0),
	(9, 120, 1, 0, 0, 0, 0),
	(10, 214, 9, 1, 0, 0, 0),
	(11, 408, 1, 0, 0, 0, 0),
	(12, 112, 1, 0, 0, 0, 0);
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
  `dormBuildId` int DEFAULT NULL COMMENT '宿舍楼id',
  `roomId` int DEFAULT NULL COMMENT '寝室号',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `class` int DEFAULT NULL COMMENT '班级号',
  `leaveSchool` int NOT NULL DEFAULT '0' COMMENT '是否离校(0在校 1离校)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stu_code` (`stu_code`),
  KEY `FK_user_dormbuild` (`dormBuildId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- 正在导出表  dorm.user 的数据：~15 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `password`, `roleId`, `stu_code`, `sex`, `major`, `dormBuildId`, `roomId`, `tel`, `class`, `leaveSchool`) VALUES
	(1, 'admin', '123', 0, '110', 0, NULL, NULL, NULL, '17777777777', NULL, 0),
	(2, 'keeper1', '123', 1, '10000', 1, NULL, 9, NULL, '17777777777', NULL, 0),
	(4, '诸葛亮', '123', 2, '2018217667', 1, NULL, 5, NULL, '17777777777', NULL, 0),
	(5, '孙悟空', '123', 2, '2018217668', 1, '计算机', 1, 101, '1777777777', 1, 0),
	(7, '孙膑', '123', 2, '2018217669', 1, '计算机', 4, 101, '17777777777', 1, 0),
	(8, '刘备', '123', 2, '2018217670', 1, '计算机', 5, 101, '17777777777', 1, 0),
	(9, '1', '123', 2, '2018217631', 1, '计算机', 1, 112, '123', 1, 0),
	(10, '蔡哲锐', '1', 2, '2018217632', 1, '计算机', 9, 214, '17756921990', 2, 1),
	(12, '1', '1', 2, '2018217600', 1, '计算机', 1, 112, '132', 1, 0),
	(16, '嬴政', '123', 2, '2018216190', 1, '数学', 6, 101, '13888888888', 5, 0),
	(17, '朱熹', '123', 2, '2018217680', 1, '语文', 5, 101, '13333333333', 2, 0),
	(18, '李白', '123', 2, '2018217666', 1, '计算机', 4, 101, '17777777777', 1, 0),
	(19, '赵云', '123', 2, '2018217621', 1, '计算机', 9, 406, '17777777777', 2, 0),
	(20, '图灵', '123', 2, '2018217622', 1, '计算机', 9, 406, '17777777777', 1, 0),
	(21, '吕布', '123', 2, '2018217623', 1, '计算机', 9, 406, '17777777777', 2, 0),
	(22, '李广', '123', 2, '2018217624', 1, '计算机', 9, 406, '17777777777', 1, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  视图 dorm.userandroom 结构
-- 创建临时表以解决视图依赖性错误
CREATE TABLE `userandroom` (
	`id` INT(10) NOT NULL COMMENT '用户主键',
	`is_good` TINYINT(1) NOT NULL COMMENT '是否优秀寝室 0:是,1:否',
	`remain_water_charge` FLOAT NOT NULL COMMENT '剩余水费',
	`remain_elec_charge` FLOAT NOT NULL COMMENT '剩余电费'
) ENGINE=MyISAM;

-- 导出  表 dorm.visiter_info 结构
CREATE TABLE IF NOT EXISTS `visiter_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记姓名',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客联系号码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记原因',
  `time` datetime DEFAULT NULL COMMENT '访客登记时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- 正在导出表  dorm.visiter_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `visiter_info` DISABLE KEYS */;
REPLACE INTO `visiter_info` (`id`, `name`, `tel`, `reason`, `time`) VALUES
	(5, 'lucky', '18856357393', '山大', '2021-11-06 20:04:48'),
	(7, '姜天', '18856357393', '叭叭叭八不不不', '2021-11-07 10:11:11'),
	(10, '高圣昌', '18856357393', 'kg好家伙', '2021-11-07 10:26:59'),
	(11, '翁少勇', '18856357393', '号狂欢节', '2021-11-07 10:31:08');
/*!40000 ALTER TABLE `visiter_info` ENABLE KEYS */;

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

-- 导出  触发器 dorm.hygiene_info_after_update 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `hygiene_info_after_update` AFTER UPDATE ON `hygiene_info` FOR EACH ROW BEGIN
UPDATE room_info
SET is_good = 0
WHERE NEW.grade_18 + NEW.grade_19 + NEW.grade_20 + NEW.grade_21 >= 360
AND room_info.room_id = NEW.room_id AND room_info.build_num = NEW.build_id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 dorm.hygiene_info_after_update2 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `hygiene_info_after_update2` AFTER UPDATE ON `hygiene_info` FOR EACH ROW BEGIN
UPDATE room_info
SET is_good = 1
WHERE NEW.grade_18 + NEW.grade_19 + NEW.grade_20 + NEW.grade_21 < 360
AND room_info.room_id = NEW.room_id AND room_info.build_num = NEW.build_id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 dorm.room_info_before_insert 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `room_info_before_insert` BEFORE INSERT ON `room_info` FOR EACH ROW BEGIN

INSERT INTO hygiene_info(build_id,room_id)

VALUES(NEW.build_num,NEW.room_id);

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 dorm.user_after_update 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `user_after_update` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
UPDATE room_info
SET person_num = person_num + 1
WHERE room_info.room_id = NEW.roomId AND room_info.build_num = NEW.dormBuildId
AND OLD.roomId is NULL AND NEW.roomId IS NOT NULL;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 dorm.user_after_update2 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `user_after_update2` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
UPDATE room_info
SET person_num = person_num - 1
WHERE room_info.room_id = OLD.roomId AND room_info.build_num = NEW.dormBuildId
AND NEW.roomId is NULL AND OLD.roomId IS NOT NULL;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  视图 dorm.userandroom 结构
-- 移除临时表并创建最终视图结构
DROP TABLE IF EXISTS `userandroom`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `userandroom` AS select `user`.`id` AS `id`,`room_info`.`is_good` AS `is_good`,`room_info`.`remain_water_charge` AS `remain_water_charge`,`room_info`.`remain_elec_charge` AS `remain_elec_charge` from (`user` join `room_info`) where ((`user`.`dormBuildId` = `room_info`.`build_num`) and (`user`.`roomId` = `room_info`.`room_id`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
