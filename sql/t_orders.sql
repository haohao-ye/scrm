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

 Date: 07/09/2022 14:54:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders`  (
  `consumption_list_id` bigint NOT NULL AUTO_INCREMENT COMMENT '消费单id',
  `client_id` int NOT NULL COMMENT '客户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `quantity` int NOT NULL COMMENT '商品量',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态',
  `salesman_id` int NOT NULL COMMENT '销售员id',
  `total_amount` int NOT NULL COMMENT '总额',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `del_lable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标签',
  PRIMARY KEY (`consumption_list_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES (1, 1111, 222, 1, '已完成', 333, 100, '2022-09-07 10:51:32.961000', 'admin', '0');
INSERT INTO `t_orders` VALUES (2, 444, 555, 2, '已完成', 666, 200, '2022-09-07 11:02:22.117000', 'admin', '0');
INSERT INTO `t_orders` VALUES (3, 777, 888, 3, '已完成', 333, 300, '2022-09-07 11:02:52.500000', 'admin', '0');

SET FOREIGN_KEY_CHECKS = 1;
