/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : park10

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 06/05/2025 16:50:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appoint
-- ----------------------------
DROP TABLE IF EXISTS `appoint`;
CREATE TABLE `appoint`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `stall_id` int(10) NULL DEFAULT NULL COMMENT '车位号',
  `car_id` int(10) NULL DEFAULT NULL COMMENT '车辆',
  `start_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `status` int(10) NOT NULL COMMENT '状态',
  `total_price` double NULL DEFAULT NULL COMMENT '总价',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `score` double(10, 2) NULL DEFAULT NULL,
  `current_day` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  `car_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `month` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预约' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of appoint
-- ----------------------------
INSERT INTO `appoint` VALUES (24, 25, 24, NULL, '2025-05-07 00:00:00', '2025-05-08 00:00:00', 5, 120, '2025-05-07 21:21:32', '2025-05-07 21:21:59', 12.00, '2025-05-07', '202505070921694735.png', 2, '鲁N999Z8', '2025', 5);
INSERT INTO `appoint` VALUES (25, 25, 16, NULL, '2025-05-07 00:00:00', '2025-05-07 02:00:00', 5, 20, '2025-05-07 21:23:04', '2025-05-07 21:23:21', 2.00, '2025-05-07', '202505071845050424.png', 1, '粤R888G8', '2025', 5);
INSERT INTO `appoint` VALUES (26, 18, 24, NULL, '2025-05-07 00:00:00', NULL, 1, NULL, '2025-05-07 21:26:06', '2025-05-07 21:26:06', NULL, NULL, '202505073657603927.png', 2, '鲁LFM200', '2025', 5);
INSERT INTO `appoint` VALUES (27, 18, 19, NULL, '2025-05-07 00:00:00', NULL, 1, NULL, '2025-05-07 21:26:46', '2025-05-07 21:26:46', NULL, NULL, '202505074061856122.png', 2, '鲁LFM200', '2025', 5);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `car_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `color` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `type` int(10) NULL DEFAULT NULL COMMENT '车型',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '汽车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (8, '京A0003', '奔驰', '蓝色', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-02-26//cc53fe1b-640a-47e7-87bf-d7688067469b_u=3695787015,702773978&fm=253&fmt=auto&app=120&f=JPEG.webp', 3, 18, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `car` VALUES (13, '川A5F888', '宝马', '黑色', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-02-02//6511abae-36b3-43ed-8867-d9c6237c4c27_u=3747858586,1095010052&fm=253&fmt=auto&app=138&f=JPEG.webp', 3, 22, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `car` VALUES (14, '京A88888', '宝马', '黑色', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-02-11//5649d6f7-3a06-441c-8e5a-2c7e65a0854a_u=2305968930,3722818725&fm=253&fmt=auto&app=120&f=JPEG.webp', 3, 23, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `car` VALUES (15, '闽JB8888', '宝马', '黑色', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-19//e7c30996-9d53-44ea-8fd3-739f451835eb_500_ChsEmVwYskmARJypAAY1xxWYLEo649.jpg', 2, 24, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `car` VALUES (16, '粤R888G8', '宝马', '蓝色', 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-07//cf05c92a-8932-421d-8cb9-b42331b7e1e3_500_ChsEmVwYskmARJypAAY1xxWYLEo649.jpg', 3, 25, '2025-05-07 21:22:47', '2025-05-07 21:22:47');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `advert_id` int(10) NULL DEFAULT NULL COMMENT '广告',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (8, 19, 9, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `collect` VALUES (9, 19, 8, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `collect` VALUES (10, 19, 7, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `collect` VALUES (11, 13, 9, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `collect` VALUES (12, 13, 8, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `collect` VALUES (13, 20, 10, '2025-05-07 21:16:57', '2025-05-07 21:16:57');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `leave_message` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言内容',
  `reply_message` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复信息',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '留言人',
  `time` int(10) NULL DEFAULT NULL COMMENT '滚动速度',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '留言' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES (15, '12323', '111', 18, 15, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `leave` VALUES (16, '停车场的梯度侦察', '2222', 19, 16, '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `leave` VALUES (17, '哈哈哈，停车场真好玩', '是的没错', 20, 14, '2025-05-07 21:16:57', '2025-05-07 21:16:57');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告内容',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (6, '停车公告', '*## 公告信息*\n‌停车场是供车辆停放之场所，可以分为公共停车场和专用停车场。‌公共停车场是根据城市规划建造的，专供社会车辆停放的营业性停车场，分为路内和路外两种类型。路内停车场是指在城市道路上临时设置的停车场，主要用于满足特大型活动如游行、集会等的需求。路外停车场则是道路以外的场所，通常带有经营收费服务性质，具体包括交通性、文体性、商业性和货运车停车场。‌\n\n‌停车场是供车辆停放之场所，可以分为公共停车场和专用停车场。‌公共停车场是根据城市规划建造的，专供社会车辆停放的营业性停车场，分为路内和路外两种类型。路内停车场是指在城市道路上临时设置的停车场，主要用于满足特大型活动如游行、集会等的需求。路外停车场则是道路以外的场所，通常带有经营收费服务性质，具体包括交通性、文体性、商业性和货运车停车场。‌\n\n‌停车场是供车辆停放之场所，可以分为公共停车场和专用停车场。‌公共停车场是根据城市规划建造的，专供社会车辆停放的营业性停车场，分为路内和路外两种类型。路内停车场是指在城市道路上临时设置的停车场，主要用于满足特大型活动如游行、集会等的需求。路外停车场则是道路以外的场所，通常带有经营收费服务性质，具体包括交通性、文体性、商业性和货运车停车场。‌\n', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `notice` VALUES (7, '系统公告', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `notice` VALUES (8, '测试公告', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `notice` VALUES (9, 'xxx', '车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内容包括停车场管理及运营、维护修缮、停车场发展及谘询、设备投放、维护及支援等；同时延伸到洗车、俱乐部、汽车救援、车险代理等延伸服务项目。\n车场管理是服务业，用户体验是服务中需要重点关注的内容。而后台IT系统的支持，也对服务质量的提高，运营成本的管控起到至关重要的作用，运营管理规模越大，作用越明显，在建设管理平台的过程中，要充分挖掘数据分析的潜力，对设备运行状况，对收款人员的事后稽核，对车场整体布局，收费规则的制定，都可以分析出最优化的管理指标。车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内容包括停车场管理及运营、维护修缮、停车场发展及谘询、设备投放、维护及支援等；同时延伸到洗车、俱乐部、汽车救援、车险代理等延伸服务项目。\n车场管理是服务业，用户体验是服务中需要重点关注的内容。而后台IT系统的支持，也对服务质量的提高，运营成本的管控起到至关重要的作用，运营管理规模越大，作用越明显，在建设管理平台的过程中，要充分挖掘数据分析的潜力，对设备运行状况，对收款人员的事后稽核，对车场整体布局，收费规则的制定，都可以分析出最优化的管理指标。车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内容包括停车场管理及运营、维护修缮、停车场发展及谘询、设备投放、维护及支援等；同时延伸到洗车、俱乐部、汽车救援、车险代理等延伸服务项目。\n车场管理是服务业，用户体验是服务中需要重点关注的内容。而后台IT系统的支持，也对服务质量的提高，运营成本的管控起到至关重要的作用，运营管理规模越大，作用越明显，在建设管理平台的过程中，要充分挖掘数据分析的潜力，对设备运行状况，对收款人员的事后稽核，对车场整体布局，收费规则的制定，都可以分析出最优化的管理指标。', '2025-05-07 21:23:41', '2025-05-07 21:23:41');

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (18, '小型停车场', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `sort` VALUES (19, '中型停车场', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `sort` VALUES (22, '机械停车场', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `sort` VALUES (24, '黄金停车场', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `sort` VALUES (25, '面包停车场', '2025-05-07 21:16:57', '2025-05-07 21:16:57');
INSERT INTO `sort` VALUES (28, 'SUV停车场', '2025-05-07 21:19:47', '2025-05-07 21:19:47');

-- ----------------------------
-- Table structure for stall
-- ----------------------------
DROP TABLE IF EXISTS `stall`;
CREATE TABLE `stall`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stall_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `stall_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort_id` int(10) NULL DEFAULT NULL COMMENT '分类',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼层位置',
  `desc` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `status` int(10) NOT NULL COMMENT '状态',
  `is_banner` int(10) NOT NULL COMMENT '是否推荐',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `count` int(10) NULL DEFAULT NULL COMMENT '访问量',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `price` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '停车位' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stall
-- ----------------------------
INSERT INTO `stall` VALUES (16, '0001', '停车位', 18, '1层', '11', '11', 1, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-02-26//3fb953ea-f961-46c6-82e4-098bb2fa29a0_u=2964741818,1386605669&fm=253&fmt=auto&app=138&f=JPEG.webp', 5, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 10.00);
INSERT INTO `stall` VALUES (17, '00001', '停车位01', 18, 'B#01', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。', '### 信息\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。\n### 内容\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。\n', 1, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-02-26//41d50782-122f-48b8-8f12-36d1d2c86d18_u=4279812308,3700371361&fm=253&fmt=auto&app=138&f=JPEG.webp', 13, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 10.00);
INSERT INTO `stall` VALUES (18, '00090', '无敌停车位', 22, '1#101', '自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。', '### 详细信息\n自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。自3月2日“青糍”发病以来，中心多次联合华西等医院会诊，3月5日成功完成了肠梗阻手术。恢复过程中，“青糍”又突发肠套叠并引发二次梗阻，于3月17日进行了急诊手术，坏死肠段被顺利切除。随后，虽专家团队每天24小时精心护理，但仍未能挽留住“青糍”的生命。', 1, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-03-27//59649e11-5710-4eb4-8eb0-c042542051ee_login-bg.png', 3, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 5.00);
INSERT INTO `stall` VALUES (19, '90001', '测试功能停车场', 18, 'B#102', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。', '### 详细信息\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。', 1, 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-03-29//83f82b3b-8173-47fb-86a8-0b37ac03230d_back.png', 3, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 5.00);
INSERT INTO `stall` VALUES (20, '0002', '测试停车场', 24, 'B#101', '趵突泉泉眼位于趵突泉公园内的泺源堂前。民国二十年（1931年）四周用石砌岸。几经变化，形成长方形泉池，长30米，宽18米，深2.2米。北临泺源堂，西傍观澜亭，东架来鹤桥，南有长廊围合。泉水从地下石灰岩溶洞中涌出，每天涌出7万立方米泉水，泉水有三个出水口，最大涌水量为16.2万立方米/天。 [18]趵突泉水一年四季恒定在18℃左右。趵突泉周边的名胜古迹有泺源堂、观澜亭、尚志堂、李清照纪念堂、李苦禅纪念馆等景点。', '### 详细西悉尼\n趵突泉泉眼位于趵突泉公园内的泺源堂前。民国二十年（1931年）四周用石砌岸。几经变化，形成长方形泉池，长30米，宽18米，深2.2米。北临泺源堂，西傍观澜亭，东架来鹤桥，南有长廊围合。泉水从地下石灰岩溶洞中涌出，每天涌出7万立方米泉水，泉水有三个出水口，最大涌水量为16.2万立方米/天。 [18]趵突泉水一年四季恒定在18℃左右。趵突泉周边的名胜古迹有泺源堂、观澜亭、尚志堂、李清照纪念堂、李苦禅纪念馆等景点。趵突泉泉眼位于趵突泉公园内的泺源堂前。民国二十年（1931年）四周用石砌岸。几经变化，形成长方形泉池，长30米，宽18米，深2.2米。北临泺源堂，西傍观澜亭，东架来鹤桥，南有长廊围合。泉水从地下石灰岩溶洞中涌出，每天涌出7万立方米泉水，泉水有三个出水口，最大涌水量为16.2万立方米/天。 [18]趵突泉水一年四季恒定在18℃左右。趵突泉周边的名胜古迹有泺源堂、观澜亭、尚志堂、李清照纪念堂、李苦禅纪念馆等景点。趵突泉泉眼位于趵突泉公园内的泺源堂前。民国二十年（1931年）四周用石砌岸。几经变化，形成长方形泉池，长30米，宽18米，深2.2米。北临泺源堂，西傍观澜亭，东架来鹤桥，南有长廊围合。泉水从地下石灰岩溶洞中涌出，每天涌出7万立方米泉水，泉水有三个出水口，最大涌水量为16.2万立方米/天。 [18]趵突泉水一年四季恒定在18℃左右。趵突泉周边的名胜古迹有泺源堂、观澜亭、尚志堂、李清照纪念堂、李苦禅纪念馆等景点。', 1, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-04-05//e8867d9c-c865-4d17-8fa6-83547188c901_back.png', 2, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 10.00);
INSERT INTO `stall` VALUES (22, '900012', '测试停车位', 26, '1#111', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款', '### 介绍\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款', 2, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-02-11//8f8653fc-c2e3-4f3b-8fe2-babeaaf9f416_u=1688452062,3705873171&fm=253&fmt=auto&app=120&f=JPEG.webp', 1, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 5.00);
INSERT INTO `stall` VALUES (23, '99999', '停车位xxx', 27, '1#111', '许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。', '### 介绍\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。\n许多人员流动大、商业往来频繁的地方都会设有地上、地下的立体停车场以节省空间。在城市规划的公路两旁或开阔处一般都会划出停车位以为司机停车提供便利，但是在城市中如果在没有划出停车位的地方停车，则可以视为违章停车，执法部门可以予以进行罚款。\n', 1, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-19//620429a9-697c-4e05-8130-23bf7f24e1ba_AB1780ABA3A0B7C2F704340E2BFF571C.png', 4, '2025-05-07 21:16:57', '2025-05-07 21:16:57', 5.00);
INSERT INTO `stall` VALUES (24, '8788', '无敌停车位', 28, '1#111', '车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内', '### 详细信息\n车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内\n车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内\n车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内\n车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内\n车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内车场管理是类似于专业化酒店管理的服务行业，负责进行停车场专业化管理服务。随着中国汽车保有量的高速增长，停车场数量也随之快速增长，与之相关的停车场管理服务需求开始浮现。车场管理的主要服务内', 1, 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-07//f134343f-9a06-4885-8154-ffd992b1244f_AB1780ABA3A0B7C2F704340E2BFF571C.png', 2, '2025-05-07 21:20:31', '2025-05-07 21:20:31', 5.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` int(10) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `user_type` int(10) NULL DEFAULT NULL COMMENT '用户类型',
  `image_url` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建时间',
  `update_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新时间',
  `money` double(10, 2) NULL DEFAULT NULL,
  `score` double(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'admin', '12345', '超管', 1, '19988834333', 1, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-12-01//586066f1-ba93-4d98-8e14-66886c425297_u=1849102722,430239227&fm=253&fmt=auto&app=138&f=JPEG.webp', '29798175343@qq.com', '2025-05-07 21:16:57', '2025-05-07 21:16:57', 100.00, 55.00);
INSERT INTO `user` VALUES (18, 'tangyan', '12345', '唐嫣', 1, '19983884383', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2024-02-26//acc9c074-997e-46c8-886b-cf8d75dfffe3_微信图片_20240108090928.jpg', '39434@qq.com', '2025-05-07 21:16:57', '2025-05-07 21:16:57', 34.00, 5.00);
INSERT INTO `user` VALUES (22, 'xiaohong', '12345', '小红', 1, '19939493943', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-02-02//29e9ea31-974a-4b8a-840a-32fff57c5c9e_Snipaste_2024-12-30_20-58-27.png', '88438@qq.com', '2025-05-07 21:16:57', '2025-05-07 21:16:57', 110.00, 0.00);
INSERT INTO `user` VALUES (23, 'dajun', '12345', '大军', 1, '19394939439', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-02-11//83395234-7710-404c-80a5-dea8ebe70321_u=2462684896,3755762318&fm=253&fmt=auto&app=120&f=JPEG.webp', '737473@qq.com', '2025-05-07 21:16:57', '2025-05-07 21:16:57', 160.00, 0.00);
INSERT INTO `user` VALUES (24, 'dahei', '12345', '大黑', 1, '19394939431', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-03-19//53416e27-6258-44a6-830b-0b7e38b05e5b_ai.png', '74737@qq.com', '2025-05-07 21:16:57', '2025-05-07 21:16:57', 450.00, 0.00);
INSERT INTO `user` VALUES (25, 'xiaoye', '12345', '宵夜', 1, '18384384831', 2, 'https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2025-05-07//f757f7a9-8a96-41dd-8634-b1c7c9a9bc93_AB1780ABA3A0B7C2F704340E2BFF571C.png', '734737@qq.com', '2025-05-07 21:19:31', '2025-05-07 21:19:31', 637.00, 0.00);

SET FOREIGN_KEY_CHECKS = 1;
