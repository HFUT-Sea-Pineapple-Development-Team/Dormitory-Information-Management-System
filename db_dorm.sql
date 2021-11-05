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

 Date: 05/11/2021 15:23:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dormmanagerassociation
-- ----------------------------
DROP TABLE IF EXISTS `dormmanagerassociation`;
CREATE TABLE `dormmanagerassociation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联主键',
  `stu_code` int(11) NOT NULL COMMENT '学工号',
  `dormBuildName` int(11) NOT NULL COMMENT '宿舍楼号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宿舍宿管关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormmanagerassociation
-- ----------------------------

-- ----------------------------
-- Table structure for hygiene_info
-- ----------------------------
DROP TABLE IF EXISTS `hygiene_info`;
CREATE TABLE `hygiene_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `build_id` int(11) NULL DEFAULT NULL COMMENT '宿舍楼号',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '寝室房间号',
  `grade_18` float NULL DEFAULT NULL COMMENT '18年寝室卫生打分',
  `grade_19` float NULL DEFAULT NULL COMMENT '19年寝室卫生打分',
  `grade_20` float NULL DEFAULT NULL COMMENT '20年寝室卫生打分',
  `grade_21` float NULL DEFAULT NULL COMMENT '21年寝室卫生打分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hygiene_info
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知公告主键',
  `time` datetime NOT NULL COMMENT '公告发布时间',
  `detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for repair_info
-- ----------------------------
DROP TABLE IF EXISTS `repair_info`;
CREATE TABLE `repair_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学工号，工作人员和学生统一',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `build_id` int(11) NOT NULL COMMENT '宿舍楼号',
  `room_id` int(11) NOT NULL COMMENT '寝室房间号',
  `report_time` date NOT NULL COMMENT '报修时间',
  `repair_time` date NULL DEFAULT NULL COMMENT '维修时间',
  `idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修意见',
  `is_satisfied` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修是否满意：非常满意，满意，一般，不满意',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报修原因',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `is_finished` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修是否完成',
  `repair_report` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修完成报告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of repair_info
-- ----------------------------
INSERT INTO `repair_info` VALUES (0, '2018217632', '卢春雨', 9, 406, '2021-11-01', '2021-11-03', '从这些', '一般', '连不上校园网', '18856357393', '是', '已经修好');
INSERT INTO `repair_info` VALUES (21, '2018217666', '翁少勇', 9, 406, '2021-11-05', '2021-11-05', '速度很快', '非常满意', '大傻逼', '18856357393', '是', '已完成，注意安全');
INSERT INTO `repair_info` VALUES (23, '2018217666', '刘博', 9, 406, '2021-11-05', NULL, NULL, NULL, '大傻逼', '18856357393', NULL, NULL);
INSERT INTO `repair_info` VALUES (24, '2018217632', '蔡哲锐', 9, 403, '2021-11-05', NULL, NULL, NULL, '我不知道', '18856357393', NULL, NULL);
INSERT INTO `repair_info` VALUES (25, '2018217632', '姜天', 9, 403, '2021-11-05', NULL, NULL, NULL, '我也不知道', '18856357393', NULL, NULL);
INSERT INTO `repair_info` VALUES (26, '2018217632', '高圣昌', 9, 403, '2021-11-05', NULL, NULL, NULL, '我怎么会知道呢', '18856357393', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `roleId` int(11) NOT NULL DEFAULT 2 COMMENT '用户角色(0超级管理员 1宿舍管理员 2学生)',
  `stu_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `sex` int(11) NOT NULL COMMENT '性别(0代表女 1代表男)',
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `dormBuildId` int(11) NULL DEFAULT NULL COMMENT '宿舍楼号',
  `roomId` int(11) NULL DEFAULT NULL COMMENT '寝室号',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `class` int(11) NULL DEFAULT NULL COMMENT '班级号',
  `leave` int(11) NOT NULL DEFAULT 0 COMMENT '是否离校(0在校 1离校)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stu_code`(`stu_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', 0, '110', 0, NULL, 9, NULL, '17777777777', NULL, 0);
INSERT INTO `user` VALUES (2, 'keeper1', '123', 1, '10000', 1, NULL, 9, NULL, '17777777777', NULL, 0);
INSERT INTO `user` VALUES (3, '李白', '123', 2, '2018217666', 1, NULL, 9, 406, '17777777777', NULL, 0);
INSERT INTO `user` VALUES (4, 'lucky', '456', 2, '2018217632', 1, NULL, 9, 403, '18856357393', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of visit_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
