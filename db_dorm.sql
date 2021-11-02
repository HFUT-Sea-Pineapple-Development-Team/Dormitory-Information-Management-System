/*
 Navicat Premium Data Transfer

 Source Server         : lucky
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : db_dorm

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 02/11/2021 18:16:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hygiene_info
-- ----------------------------
DROP TABLE IF EXISTS `hygiene_info`;
CREATE TABLE `hygiene_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `build_id` int(2) NULL DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int(3) NULL DEFAULT NULL COMMENT '寝室房间号',
  `grade_18` float NULL DEFAULT NULL COMMENT '18年寝室卫生打分',
  `grade_19` float NULL DEFAULT NULL COMMENT '19年寝室卫生打分',
  `grade_20` float NULL DEFAULT NULL COMMENT '20年寝室卫生打分',
  `grade_21` float NULL DEFAULT NULL COMMENT '21年寝室卫生打分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hygiene_info
-- ----------------------------

-- ----------------------------
-- Table structure for repair_info
-- ----------------------------
DROP TABLE IF EXISTS `repair_info`;
CREATE TABLE `repair_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学工号，工作人员和学生统一',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `build_id` int(2) NULL DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int(3) NULL DEFAULT NULL COMMENT '寝室房间号',
  `report_time` datetime NULL DEFAULT NULL COMMENT '报修时间',
  `repair_time` datetime NULL DEFAULT NULL COMMENT '维修时间',
  `is_repair` tinyint(1) NULL DEFAULT NULL COMMENT '是否维修成功；0：已维修成功；1：未维修成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_info
-- ----------------------------

-- ----------------------------
-- Table structure for room_info
-- ----------------------------
DROP TABLE IF EXISTS `room_info`;
CREATE TABLE `room_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NULL DEFAULT NULL COMMENT '寝室房间号',
  `person_num` int(11) NULL DEFAULT NULL COMMENT '寝室人数',
  `remain_water_charge` float NULL DEFAULT NULL COMMENT '剩余水费',
  `remain_elec_charge` float NULL DEFAULT NULL COMMENT '剩余电费',
  `is_good` tinyint(1) NOT NULL COMMENT '是否优秀寝室 0:是,1:否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_info
-- ----------------------------

-- ----------------------------
-- Table structure for visit_info
-- ----------------------------
DROP TABLE IF EXISTS `visit_info`;
CREATE TABLE `visit_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客登记姓名',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客联系号码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客登记原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of visit_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
