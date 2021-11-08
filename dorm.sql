-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: dorm
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dormbuild`
--

DROP TABLE IF EXISTS `dormbuild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormbuild` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dormBuildName` int NOT NULL DEFAULT '0' COMMENT '宿舍楼名称',
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宿舍楼简介',
  `sex` int NOT NULL DEFAULT '0' COMMENT '宿舍性别类型(0女 1男)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dormBuildName` (`dormBuildName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍楼表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormbuild`
--

LOCK TABLES `dormbuild` WRITE;
/*!40000 ALTER TABLE `dormbuild` DISABLE KEYS */;
INSERT INTO `dormbuild` VALUES (1,5,'我',1),(4,4,'发送到',1),(5,9,'雨哥的宿舍',1),(6,8,'女生寝室',1),(7,100,'阿萨德',1),(8,10,'打啥',1),(9,7,'快乐的地方',1),(10,401,'游乐场',1);
/*!40000 ALTER TABLE `dormbuild` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormmanagerassociation`
--

DROP TABLE IF EXISTS `dormmanagerassociation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormmanagerassociation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关联主键',
  `stu_code` int NOT NULL COMMENT '学工号',
  `dormBuildName` int NOT NULL COMMENT '宿舍楼号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='宿舍宿管关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormmanagerassociation`
--

LOCK TABLES `dormmanagerassociation` WRITE;
/*!40000 ALTER TABLE `dormmanagerassociation` DISABLE KEYS */;
/*!40000 ALTER TABLE `dormmanagerassociation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hygiene_info`
--

DROP TABLE IF EXISTS `hygiene_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hygiene_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `build_id` int DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int DEFAULT NULL COMMENT '寝室房间号',
  `grade_18` float DEFAULT NULL COMMENT '18年寝室卫生打分',
  `grade_19` float DEFAULT NULL COMMENT '19年寝室卫生打分',
  `grade_20` float DEFAULT NULL COMMENT '20年寝室卫生打分',
  `grade_21` float DEFAULT NULL COMMENT '21年寝室卫生打分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hygiene_info`
--

LOCK TABLES `hygiene_info` WRITE;
/*!40000 ALTER TABLE `hygiene_info` DISABLE KEYS */;
INSERT INTO `hygiene_info` VALUES (1,9,406,100,90,95,90),(2,9,407,80,85,90,95),(3,1,101,100,100,100,100),(5,9,408,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hygiene_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `hygiene_info_after_update` AFTER UPDATE ON `hygiene_info` FOR EACH ROW BEGIN
UPDATE room_info
SET is_good = 0
WHERE NEW.grade_18 + NEW.grade_19 + NEW.grade_20 + NEW.grade_21 >= 360
AND room_info.room_id = NEW.room_id AND room_info.build_num = NEW.build_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `hygiene_info_after_update2` AFTER UPDATE ON `hygiene_info` FOR EACH ROW BEGIN
UPDATE room_info
SET is_good = 1
WHERE NEW.grade_18 + NEW.grade_19 + NEW.grade_20 + NEW.grade_21 < 360
AND room_info.room_id = NEW.room_id AND room_info.build_num = NEW.build_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '通知公告主键',
  `time` datetime NOT NULL COMMENT '公告发布时间',
  `detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '公告内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_info`
--

DROP TABLE IF EXISTS `repair_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_info` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_info`
--

LOCK TABLES `repair_info` WRITE;
/*!40000 ALTER TABLE `repair_info` DISABLE KEYS */;
INSERT INTO `repair_info` VALUES (21,'2018217666','翁少勇',9,406,'2021-11-05','2021-11-05','速度很快','非常满意','大傻逼','18856357393','是','已完成，注意安全'),(24,'2018217632','蔡哲锐',9,403,'2021-11-05',NULL,NULL,NULL,'我不知道','18856357393',NULL,NULL),(25,'2018217632','姜天',9,403,'2021-11-05',NULL,NULL,NULL,'我也不知道','18856357393',NULL,NULL),(27,'2018217632','卢春雨',9,406,'2021-11-01','2021-11-03','从这些','一般','连不上校园网','18856357393','是','已经修好');
/*!40000 ALTER TABLE `repair_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL COMMENT '寝室房间号',
  `build_num` int NOT NULL COMMENT '楼号',
  `person_num` int DEFAULT NULL COMMENT '寝室人数',
  `remain_water_charge` float DEFAULT NULL COMMENT '剩余水费',
  `remain_elec_charge` float DEFAULT NULL COMMENT '剩余电费',
  `is_good` tinyint(1) NOT NULL COMMENT '是否优秀寝室 0:是,1:否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_info`
--

LOCK TABLES `room_info` WRITE;
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
INSERT INTO `room_info` VALUES (1,101,1,2,30,50,1),(2,406,9,1,30,60,0),(3,408,9,0,100,100,0);
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `room_info_before_insert` BEFORE INSERT ON `room_info` FOR EACH ROW BEGIN

INSERT INTO hygiene_info(build_id,room_id)

VALUES(NEW.build_num,NEW.room_id);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
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
  KEY `FK_user_dormbuild` (`dormBuildId`),
  CONSTRAINT `FK_user_dormbuild` FOREIGN KEY (`dormBuildId`) REFERENCES `dormbuild` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123',0,'110',0,NULL,NULL,NULL,'17777777777',NULL,0),(2,'keeper1','123',1,'10000',1,NULL,9,NULL,'17777777777',NULL,0),(4,'诸葛亮','123',2,'2018217667',1,NULL,5,NULL,'17777777777',NULL,0),(5,'孙悟空','123',2,'2018217668',1,'计算机',1,101,'1777777777',1,0),(7,'孙膑','123',2,'2018217669',1,'计算机',4,101,'17777777777',1,0),(8,'刘备','123',2,'2018217670',1,'计算机',5,101,'17777777777',1,0),(9,'1','123',2,'2018217631',1,'计算机',1,123,'123',1,0),(10,'蔡哲锐','1',2,'2018217632',1,'计算机',9,406,'17756921990',2,1),(12,'1','1',2,'2018217600',1,'计算机',1,123,'132',1,0),(16,'嬴政','123',2,'2018216190',1,'数学',6,301,'13888888888',5,0),(17,'朱熹','123',2,'2018217680',1,'语文',5,201,'13333333333',2,0),(18,'李白','123',2,'2018217666',1,'计算机',4,101,'17777777777',1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `userandroom`
--

DROP TABLE IF EXISTS `userandroom`;
/*!50001 DROP VIEW IF EXISTS `userandroom`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `userandroom` AS SELECT 
 1 AS `id`,
 1 AS `is_good`,
 1 AS `remain_water_charge`,
 1 AS `remain_elec_charge`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `visit_info`
--

DROP TABLE IF EXISTS `visit_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记姓名',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客联系号码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访客登记原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit_info`
--

LOCK TABLES `visit_info` WRITE;
/*!40000 ALTER TABLE `visit_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `visit_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `userandroom`
--

/*!50001 DROP VIEW IF EXISTS `userandroom`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `userandroom` AS select `user`.`id` AS `id`,`room_info`.`is_good` AS `is_good`,`room_info`.`remain_water_charge` AS `remain_water_charge`,`room_info`.`remain_elec_charge` AS `remain_elec_charge` from (`user` join `room_info`) where ((`user`.`dormBuildId` = `room_info`.`build_num`) and (`user`.`roomId` = `room_info`.`room_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-05 21:57:21
