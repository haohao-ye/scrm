/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : myscrm_db

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 22/09/2022 10:01:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'https://s1.ax1x.com/2022/09/16/vz2BTK.png' COMMENT '头像',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后更新人',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '花开富贵', '15423654789', '0', 'admin', '2022-08-30 14:55:41', 'admin', '2022-08-30 14:55:52', '超级管理员');
INSERT INTO `t_admin` VALUES (2, 'ross123', '123456', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '往事随风', '15622478412', '0', 'admin', '2022-08-30 16:58:27', 'admin', '2022-08-30 16:58:34', '管理员');
INSERT INTO `t_admin` VALUES (3, 'Quagmire', '123456', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '啊Q', '12513541254', '0', 'admin', '2022-09-01 10:11:34', 'admin', '2022-09-01 10:11:34', NULL);
INSERT INTO `t_admin` VALUES (4, 'jinin', '111111', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '宁津', '15678521763', '0', 'admin', '2022-09-01 10:34:13', 'admin', '2022-09-01 10:34:13', NULL);
INSERT INTO `t_admin` VALUES (7, 'Joanau', '111111', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '王二狗', '15116521165', '0', 'admin', '2022-09-01 16:05:51', 'admin', '2022-09-01 16:05:51', '');
INSERT INTO `t_admin` VALUES (8, 'Maria', '111111', 'https://s1.ax1x.com/2022/09/16/vz2BTK.png', '欧阳丽', '12345678688', '0', 'admin', '2022-09-01 16:06:46', 'admin', '2022-09-01 16:06:46', '111');

SET FOREIGN_KEY_CHECKS = 1;
