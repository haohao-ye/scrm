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

 Date: 08/09/2022 14:00:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  `price` double NOT NULL COMMENT '价格',
  `lable` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '标签',
  `brand` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  `inventory` bigint NOT NULL COMMENT '库存',
  `color` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色',
  `activity_id` int NULL DEFAULT NULL COMMENT '折扣',
  `creat_time` datetime(6) NOT NULL COMMENT '创建时间',
  `creat_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '5555', '1', 444, '4444', '4444', 4444, '444', 1, '2022-09-07 08:00:00.000000', 'wr', '2022-09-08 12:50:37.405000', 'ss', '0');
INSERT INTO `t_goods` VALUES (3, 'aa', '1', 123, 'sd', 'sada', 23, 'sdf', 1, '2022-09-07 08:00:00.000000', 'ygy', '2022-09-08 12:50:30.928000', NULL, '0');
INSERT INTO `t_goods` VALUES (4, '12', 'pc', 212, '22', '1221', 212, '1221', 1, '2022-09-07 15:05:09.564000', 'admin', '2022-09-07 15:05:09.564000', NULL, '1');
INSERT INTO `t_goods` VALUES (6, '23', 'pc', 22.2, '', 'jkl', 45, NULL, NULL, '2022-09-07 16:55:47.475000', 'admin', '2022-09-07 16:55:47.475000', NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
