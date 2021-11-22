/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : kuangstudy

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 22/11/2021 17:47:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ks_blog
-- ----------------------------
DROP TABLE IF EXISTS `ks_blog`;
CREATE TABLE `ks_blog`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `bid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客id',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客内容',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序 0 普通  1 置顶',
  `views` int NOT NULL DEFAULT 0 COMMENT '浏览量',
  `author_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者id',
  `author_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者名',
  `author_avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者头像',
  `category_id` int NOT NULL COMMENT '问题分类id',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题分类名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_update` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_blog
-- ----------------------------
INSERT INTO `ks_blog` VALUES (30, '9b04d32210d24b1397e944fe87453157', '沙发的说法', ' 舒服', 0, 5, '464a084dd56a45a69a735a4a2e3f0db3', 'yzqdev', '/images/avatar/avatar-1.jpg', 1, '222', '2021-11-21 22:12:44', '2021-11-21 22:20:53');

-- ----------------------------
-- Table structure for ks_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `ks_blog_category`;
CREATE TABLE `ks_blog_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_blog_category
-- ----------------------------
INSERT INTO `ks_blog_category` VALUES (1, '222');

-- ----------------------------
-- Table structure for ks_comment
-- ----------------------------
DROP TABLE IF EXISTS `ks_comment`;
CREATE TABLE `ks_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `comment_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论唯一id',
  `topic_category` int NOT NULL COMMENT '1博客 2问答',
  `topic_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论主题id',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者id',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者昵称',
  `user_avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者头像',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `gmt_create` datetime NOT NULL COMMENT '评论创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_comment
-- ----------------------------
INSERT INTO `ks_comment` VALUES (137, 'db3ed344ef2c489ba701a17edbae9ad0', 2, 'fb850442eba54bcdb0111a8395e898db', '464a084dd56a45a69a735a4a2e3f0db3', 'yzqdev', '/images/avatar/avatar-1.jpg', '方法', '2021-11-21 22:11:38');

-- ----------------------------
-- Table structure for ks_download
-- ----------------------------
DROP TABLE IF EXISTS `ks_download`;
CREATE TABLE `ks_download`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `dname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名',
  `ddesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源链接',
  `dcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提取码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_download
-- ----------------------------

-- ----------------------------
-- Table structure for ks_invite
-- ----------------------------
DROP TABLE IF EXISTS `ks_invite`;
CREATE TABLE `ks_invite`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邀请码',
  `uid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态 0 未使用 1 使用',
  `active_time` datetime NULL DEFAULT NULL COMMENT '激活时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1217 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_invite
-- ----------------------------
INSERT INTO `ks_invite` VALUES (1, '111', '4a6c46eda2254985818e4c8d08a9b879', 1, '2021-11-22 16:32:46', '2021-11-28 22:04:20');

-- ----------------------------
-- Table structure for ks_question
-- ----------------------------
DROP TABLE IF EXISTS `ks_question`;
CREATE TABLE `ks_question`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `qid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题id',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题内容',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态 0 未解决 1 已解决',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序 0 普通  1 置顶',
  `views` int NOT NULL DEFAULT 0 COMMENT '浏览量',
  `author_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者id',
  `author_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者名',
  `author_avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者头像',
  `category_id` int NOT NULL COMMENT '问题分类id',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题分类名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_update` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_question
-- ----------------------------
INSERT INTO `ks_question` VALUES (35, 'fb850442eba54bcdb0111a8395e898db', '舒服舒服', ' 舒服舒服', 1, 0, 6, '464a084dd56a45a69a735a4a2e3f0db3', 'yzqdev', '/images/avatar/avatar-1.jpg', 1, '222', '2021-11-21 22:11:33', '2021-11-21 22:21:55');

-- ----------------------------
-- Table structure for ks_question_category
-- ----------------------------
DROP TABLE IF EXISTS `ks_question_category`;
CREATE TABLE `ks_question_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_question_category
-- ----------------------------
INSERT INTO `ks_question_category` VALUES (1, '222');

-- ----------------------------
-- Table structure for ks_say
-- ----------------------------
DROP TABLE IF EXISTS `ks_say`;
CREATE TABLE `ks_say`  (
  `id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一id',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `gmt_create` datetime NOT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_say
-- ----------------------------

-- ----------------------------
-- Table structure for ks_user
-- ----------------------------
DROP TABLE IF EXISTS `ks_user`;
CREATE TABLE `ks_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `role_id` int NOT NULL COMMENT '角色编号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/images/avatar/avatar-1.jpg' COMMENT '头像',
  `login_date` datetime NOT NULL COMMENT '登录时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 840 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_user
-- ----------------------------
INSERT INTO `ks_user` VALUES (1, '4a6c46eda2254985818e4c8d08a9b879', 2, 'string', 'string', '/images/avatar/avatar-1.jpg', '2021-11-22 16:32:46', '2021-11-22 16:32:46');

-- ----------------------------
-- Table structure for ks_user_donate
-- ----------------------------
DROP TABLE IF EXISTS `ks_user_donate`;
CREATE TABLE `ks_user_donate`  (
  `uid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `donate_json` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '赞赏二维码信息'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_user_donate
-- ----------------------------

-- ----------------------------
-- Table structure for ks_user_info
-- ----------------------------
DROP TABLE IF EXISTS `ks_user_info`;
CREATE TABLE `ks_user_info`  (
  `uid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `nickname` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `realname` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `wechat` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'WeChat',
  `email` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `work` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `hobby` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `intro` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我介绍'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_user_info
-- ----------------------------
INSERT INTO `ks_user_info` VALUES ('464a084dd56a45a69a735a4a2e3f0db3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ks_user_info` VALUES ('607999205e704c49bfe17f75764f4776', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ks_user_info` VALUES ('4a6c46eda2254985818e4c8d08a9b879', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ks_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ks_user_role`;
CREATE TABLE `ks_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无描述...' COMMENT '角色描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks_user_role
-- ----------------------------
INSERT INTO `ks_user_role` VALUES (2, '11', '无描述...', '2021-11-21 22:05:44');

SET FOREIGN_KEY_CHECKS = 1;
