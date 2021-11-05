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

 Date: 05/11/2021 15:32:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
