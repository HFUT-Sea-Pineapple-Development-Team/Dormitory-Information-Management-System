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

 Date: 07/11/2021 10:41:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `receivers_num` int(11) NOT NULL DEFAULT 0 COMMENT '收到通知人数',
  `stu_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学工号',
  `time` date NOT NULL COMMENT '公告发布时间',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_icelandic_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stu_id`(`stu_id`) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `user` (`stu_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_icelandic_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '疫情', '不准出校门', 6, '10000', '2021-11-05', '18856357393');
INSERT INTO `notice` VALUES (3, '寒假时间', '寒假开始啦，同学们新年快乐', 6, '10000', '2021-11-06', '18856357393');
INSERT INTO `notice` VALUES (4, '放假注意事项', '注意安全', 5, '10000', '2021-11-08', '18856357393');
INSERT INTO `notice` VALUES (8, '降温', '多穿衣服', 12, '10000', '2021-11-06', '18856357393');
INSERT INTO `notice` VALUES (9, NULL, NULL, 4, '10000', '2021-11-06', NULL);

SET FOREIGN_KEY_CHECKS = 1;
