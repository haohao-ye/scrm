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

 Date: 19/09/2022 10:57:17
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
  `client_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户名字',
  `goods_id` int NOT NULL COMMENT '商品id',
  `goods_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名字',
  `quantity` int NOT NULL COMMENT '商品量',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品照片',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态',
  `salesman_id` int NOT NULL COMMENT '销售员id',
  `total_amount` int NOT NULL COMMENT '总额',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `del_lable` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标签',
  PRIMARY KEY (`consumption_list_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES (1, 1111, '华农', 1, '华为商务本G1', 1, 'https://img0.baidu.com/it/u=16615065,2327927249&fm=253&fmt=auto&app=120&f=PNG?w=596&h=767', '待付款', 333, 10000, '2022-09-07 10:51:32.961000', 'admin', '0');
INSERT INTO `t_orders` VALUES (2, 444, '泰山', 2, '华为商务本G2', 2, 'https://img1.baidu.com/it/u=4280752728,754138128&fm=253&fmt=auto&app=120&f=PNG?w=491&h=341', '已出库', 666, 19999, '2022-09-07 11:02:22.117000', 'admin', '0');
INSERT INTO `t_orders` VALUES (3, 777, '华山', 4, '华为商务本G3', 3, 'https://img0.baidu.com/it/u=4224244144,1282171132&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500', '待收货', 333, 28888, '2022-09-07 11:02:52.500000', 'admin', '0');
INSERT INTO `t_orders` VALUES (5, 222, '启林', 6, '华为商务本G4', 4, 'https://img0.baidu.com/it/u=3644849022,1183103850&fm=253&fmt=auto&app=138&f=JPEG?w=574&h=363', '已完成', 32, 38888, '2022-09-15 11:42:05.358000', 'admin', '0');

SET FOREIGN_KEY_CHECKS = 1;
