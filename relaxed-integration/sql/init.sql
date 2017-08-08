/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : relaxed_integration

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 05/08/2017 21:02:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ruser
-- ----------------------------
DROP TABLE IF EXISTS `ruser`;
CREATE TABLE `ruser`  (
  `rUserId` int(11) NOT NULL AUTO_INCREMENT COMMENT '整合系统的userId',
  `s1UserId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '子系统1的userId',
  `s2UserId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '子系统2的userId',
  `s3UserId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '子系统3的userId',
  `feature1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用于匹配userId的特征值1',
  `feature2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用于匹配userId的特征值2',
  `feature3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用于匹配userId的特征值3',
  PRIMARY KEY (`rUserId`) USING BTREE,
  INDEX `index_ruser_s1UserId`(`s1UserId`) USING BTREE,
  INDEX `index_ruser_s2UserId`(`s2UserId`) USING BTREE,
  INDEX `index_ruser_s3UserId`(`s3UserId`) USING BTREE,
  INDEX `index_ruser_feature1`(`feature1`) USING BTREE,
  INDEX `index_ruser_feature2`(`feature2`) USING BTREE,
  INDEX `index_ruser_feature3`(`feature3`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
