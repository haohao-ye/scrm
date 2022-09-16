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

 Date: 16/09/2022 09:24:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`  (
  `id` bigint NOT NULL COMMENT '员工工号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `gender` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工性别',
  `id_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工身份证',
  `phone_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `password` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系统登录密码',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '所属部门',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_dept_id`(`dept_id`) USING BTREE,
  CONSTRAINT `fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (20200902, '王武', '男', '400134188305052659', '12548756931', '18830505', 1);
INSERT INTO `t_employee` VALUES (20210603, '里夫金', '女', '442158199603052124', '12544785524', '19960305', 1);
INSERT INTO `t_employee` VALUES (20210804, '倪狻', '男', '445124200002052114', '13666558877', '20000205', 2);
INSERT INTO `t_employee` VALUES (20220900, '李逍遥', '男', '188201200106052215', '15233698874', '20010605', 2);
INSERT INTO `t_employee` VALUES (20220901, '江离', '男', '440125199909013114', '13521465541', '19990901', 2);
INSERT INTO `t_employee` VALUES (20220905, '林泽坤', '男', '254236199208253114', '15422647777', '19920825', 4);
INSERT INTO `t_employee` VALUES (20220906, '牛一', '女', '220114199602250220', '13542145574', '19960225', 1);
INSERT INTO `t_employee` VALUES (20220907, '随之然', '男', '110254197911243154', '16547784495', '19791124', 4);
INSERT INTO `t_employee` VALUES (20220908, '凉', '男', '152365199901019913', '12365413324', '19990101', 4);

SET FOREIGN_KEY_CHECKS = 1;
