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

 Date: 21/09/2022 09:42:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_client
-- ----------------------------
DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `client_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户地址',
  `phone_number` bigint NOT NULL COMMENT '客户联系方式',
  `client_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户级别',
  `client_group` bigint NULL DEFAULT NULL COMMENT '客户组别（由谁负责）',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) NOT NULL COMMENT '更新时间',
  `update_person` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_label` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标签',
  `contact_label` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '联系标签',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `clientGroup_foreign`(`client_group`) USING BTREE,
  CONSTRAINT `clientGroup_foreign` FOREIGN KEY (`client_group`) REFERENCES `t_employee` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_client
-- ----------------------------
INSERT INTO `t_client` VALUES (3, '天下', '广东省广州市天河区五山街道482号', 15435425412, 'B类客户', 20210603, '2022-09-07 08:00:00.000000', 'admin', '2022-09-21 09:34:08.920000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (4, '天下第四', '广东省广州市天河区五山街道483号', 15435425412, 'B类客户', 20200902, '2022-09-07 08:00:00.000000', 'admin', '2022-09-20 17:24:08.250000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (5, '天下第五', '广东省广州市天河区五山街道483号', 15435425412, '啊啊啊啊啊啊', 20200902, '2022-09-07 10:42:20.000000', 'admin', '2022-09-07 10:42:54.000000', 'admin', '0', '1');
INSERT INTO `t_client` VALUES (6, '我要成为第一', NULL, 15425412541, NULL, 20200902, '2022-09-07 08:00:00.000000', 'admin', '2022-09-13 09:58:59.442000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (7, '天下老末', NULL, 15424552475, '呜啦啦啦啦', 20200902, '2022-09-13 08:00:00.000000', 'admin', '2022-09-13 09:59:43.131000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (9, '天下无贼', NULL, 15425452651, NULL, 20200902, '2022-09-13 10:16:43.000000', 'admin', '2022-09-13 10:16:43.000000', 'admin', '0', '1');
INSERT INTO `t_client` VALUES (10, '张三', '火星', 15452545236, '呜啦啦啦啦', 20200902, '2022-09-16 14:43:31.000000', 'admin', '2022-09-16 14:43:31.000000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (11, '李四', '月球', 110, '真刑啊', 20200902, '2022-09-16 14:44:43.000000', 'admin', '2022-09-16 14:44:43.000000', 'admin', '0', '1');
INSERT INTO `t_client` VALUES (12, '王五', '地球', 111, '呜呜呜', 20200902, '2022-09-16 14:49:10.000000', 'admin', '2022-09-16 14:49:10.000000', 'admin', '0', '1');
INSERT INTO `t_client` VALUES (13, '赵四', '冥王星', 112, '外形旅客', 20200902, '2022-09-16 14:49:55.000000', 'admin', '2022-09-16 14:49:55.000000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (14, '玛卡巴卡', '花园', 113, '啦啦啦', 20200902, '2022-09-16 14:53:46.000000', 'admin', '2022-09-16 14:53:46.000000', 'admin', '0', '1');
INSERT INTO `t_client` VALUES (15, '路西', '美国', 1115, '外国人', 20200902, '2022-09-16 14:56:30.000000', 'admin', '2022-09-16 14:56:30.000000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (20, '张强', '广东省广州市天河区五山街道483号', 15485214752, 'B类客户', 20220900, '2022-09-20 16:45:09.000000', 'admin', '2022-09-20 16:45:09.000000', 'admin', '0', '0');
INSERT INTO `t_client` VALUES (21, '王丽', '广东省广州市天河区五山街道483号', 15825495254, 'C类客户', 20220901, '2022-09-21 09:36:37.000000', 'admin', '2022-09-21 09:36:37.000000', 'admin', '0', '0');

SET FOREIGN_KEY_CHECKS = 1;
