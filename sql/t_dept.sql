/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : myscrm_db

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 07/09/2022 11:29:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `dept_no` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `create_by` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_no`) USING BTREE,
  INDEX `dept_no`(`dept_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '销售部', NULL, '2022-09-06 16:29:05', NULL, '2022-09-07 11:11:20', '555');

SET FOREIGN_KEY_CHECKS = 1;
