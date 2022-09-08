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

 Date: 08/09/2022 14:00:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `create_by` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_no`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '销售部', NULL, '2022-09-06 16:29:05', NULL, '2022-09-07 11:11:20', '555', '0');
INSERT INTO `t_dept` VALUES (2, '开发部', 'admin', '2022-09-07 15:48:33', NULL, '2022-09-07 15:48:33', '10000', '0');
INSERT INTO `t_dept` VALUES (3, '开发部', 'admin', '2022-09-07 15:48:33', NULL, '2022-09-07 15:48:33', '10000', '0');
INSERT INTO `t_dept` VALUES (4, '营业部', 'admin', '2022-09-07 15:48:50', NULL, '2022-09-07 15:48:50', '80000', '0');
INSERT INTO `t_dept` VALUES (5, '营业部', 'admin', '2022-09-07 15:48:50', NULL, '2022-09-07 15:48:50', '80000', '0');
INSERT INTO `t_dept` VALUES (6, '营业部', 'admin', '2022-09-07 15:48:50', NULL, '2022-09-07 15:48:50', '80000', '0');
INSERT INTO `t_dept` VALUES (7, 'sfd', 'admin', '2022-09-07 16:12:06', NULL, '2022-09-07 16:12:05', NULL, '1');
INSERT INTO `t_dept` VALUES (8, '666', 'admin', '2022-09-07 17:21:05', NULL, '2022-09-07 17:21:05', NULL, '0');
INSERT INTO `t_dept` VALUES (9, '11', 'admin', '2022-09-07 17:21:36', NULL, '2022-09-07 17:21:36', NULL, '0');
INSERT INTO `t_dept` VALUES (10, '1111', 'admin', '2022-09-07 17:21:52', NULL, '2022-09-07 17:21:52', NULL, '0');
INSERT INTO `t_dept` VALUES (11, '1111111', 'admin', '2022-09-07 17:25:41', NULL, '2022-09-07 17:25:41', NULL, '0');
INSERT INTO `t_dept` VALUES (12, '111', 'admin', '2022-09-07 17:43:40', NULL, '2022-09-07 17:43:40', NULL, '0');
INSERT INTO `t_dept` VALUES (13, '1111111111111111', 'admin', '2022-09-07 17:43:56', NULL, '2022-09-07 17:43:56', NULL, '0');
INSERT INTO `t_dept` VALUES (14, '111111111111111', 'admin', '2022-09-07 17:44:05', NULL, '2022-09-07 17:44:05', NULL, '0');
INSERT INTO `t_dept` VALUES (15, 'aa', 'admin', '2022-09-08 09:38:35', NULL, '2022-09-08 09:38:35', NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
