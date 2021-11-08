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

 Date: 07/11/2021 10:41:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for visiter_info
-- ----------------------------
DROP TABLE IF EXISTS `visiter_info`;
CREATE TABLE `visiter_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客登记姓名',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客联系号码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访客登记原因',
  `time` datetime NULL DEFAULT NULL COMMENT '访客登记时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of visiter_info
-- ----------------------------
INSERT INTO `visiter_info` VALUES (5, 'lucky', '18856357393', '山大', '2021-11-06 20:04:48');
INSERT INTO `visiter_info` VALUES (7, '姜天', '18856357393', '叭叭叭八不不不', '2021-11-07 10:11:11');
INSERT INTO `visiter_info` VALUES (10, '高圣昌', '18856357393', 'kg好家伙', '2021-11-07 10:26:59');
INSERT INTO `visiter_info` VALUES (11, '翁少勇', '18856357393', '号狂欢节', '2021-11-07 10:31:08');

SET FOREIGN_KEY_CHECKS = 1;
