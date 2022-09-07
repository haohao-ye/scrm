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

 Date: 07/09/2022 11:16:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_inform
-- ----------------------------
DROP TABLE IF EXISTS `t_inform`;
CREATE TABLE `t_inform`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `info_title` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知标题',
  `info_adm` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否通知管理员，1为通知，0为不通知',
  `info_sol` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '是否通知销售员',
  `info_inv` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否通知仓库管理员',
  `info_body` varchar(511) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知具体内容',
  `create_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '最后一次修改人',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后一次修改时间',
  `deadline` datetime(0) NOT NULL COMMENT '通知消失的时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_inform
-- ----------------------------
INSERT INTO `t_inform` VALUES (1, '9月32日团建活动通知', '是', '否', '否', '我司将于9月32日组织一场团建活动，请各部门安排好工作，统计参与的成员名单，秉承非必要不请假原则，落实到每一名员工', 'admin', '2022-09-07 11:00:55', 'admin', '2022-09-07 11:14:06', '2022-09-22 08:00:00', '0');
INSERT INTO `t_inform` VALUES (2, '10月国庆节放假通知', '是', '是', '是', '根据国家安排，我司于10月1日至10月7日放假。', 'admin', '2022-09-07 11:01:40', 'admin', '2022-09-07 11:15:05', '2022-09-22 08:00:00', '0');

SET FOREIGN_KEY_CHECKS = 1;
