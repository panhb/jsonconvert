/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : jsonconvert

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 20/11/2020 10:42:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_module_object
-- ----------------------------
DROP TABLE IF EXISTS `t_module_object`;
CREATE TABLE `t_module_object`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `object_id` bigint(20) NULL DEFAULT NULL COMMENT '对象id',
  `module_id` bigint(20) NULL DEFAULT NULL COMMENT '模块id',
  `sys_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统code',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_object
-- ----------------------------
DROP TABLE IF EXISTS `t_object`;
CREATE TABLE `t_object`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对象code',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_object_prop
-- ----------------------------
DROP TABLE IF EXISTS `t_object_prop`;
CREATE TABLE `t_object_prop`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `object_id` bigint(20) NULL DEFAULT NULL COMMENT '对象id',
  `root_object_id` bigint(20) NULL DEFAULT NULL COMMENT '根对象id',
  `prop_id` bigint(20) NULL DEFAULT NULL COMMENT '属性id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prop
-- ----------------------------
DROP TABLE IF EXISTS `t_prop`;
CREATE TABLE `t_prop`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属性名',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '属性类型',
  `object_id` bigint(20) NULL DEFAULT NULL COMMENT '属性对象id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prop_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_prop_mapping`;
CREATE TABLE `t_prop_mapping`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `src_prop_id` bigint(20) NULL DEFAULT NULL COMMENT '源属性',
  `target_prop_id` bigint(20) NULL DEFAULT NULL COMMENT '目标属性',
  `null_verify` tinyint(1) NULL DEFAULT NULL COMMENT '源属性值非空校验',
  `regular_verify` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '源属性值正则校验',
  `default_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '无源属性时默认值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
