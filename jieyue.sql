/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : jieyue

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 30/05/2021 17:25:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_access
-- ----------------------------
DROP TABLE IF EXISTS `sys_access`;
CREATE TABLE `sys_access`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限对应的地址',
  `status` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_access
-- ----------------------------
INSERT INTO `sys_access` VALUES (1, '权限管理页面', '/admin/rbac', 1);
INSERT INTO `sys_access` VALUES (2, '后台主页', '/admin/home', 1);
INSERT INTO `sys_access` VALUES (3, '广告设置页面', '/admin/ui', 1);
INSERT INTO `sys_access` VALUES (4, '订单列表页面', '/admin/order', 1);
INSERT INTO `sys_access` VALUES (5, '商户列表页面', '/admin/merchant/', 1);
INSERT INTO `sys_access` VALUES (6, '会员列表页面', '/admin/user', 1);
INSERT INTO `sys_access` VALUES (7, '系统消息页面', '/admin/notice', 1);

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(75) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mark` tinyint(3) NOT NULL COMMENT '管理员标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 'bosen', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@163.com', 1);

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `status` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES (1, 1, 1, 1);

-- ----------------------------
-- Table structure for sys_cart
-- ----------------------------
DROP TABLE IF EXISTS `sys_cart`;
CREATE TABLE `sys_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `goods_num` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cart
-- ----------------------------
INSERT INTO `sys_cart` VALUES (4, 40, 2, 1);
INSERT INTO `sys_cart` VALUES (5, 39, 2, 1);
INSERT INTO `sys_cart` VALUES (7, 18, 2, 1);
INSERT INTO `sys_cart` VALUES (8, 41, 1, 1);

-- ----------------------------
-- Table structure for sys_comment
-- ----------------------------
DROP TABLE IF EXISTS `sys_comment`;
CREATE TABLE `sys_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `goods` int(11) NOT NULL,
  `merchant` int(11) NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint(19) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES (17, 1, 40, 9, 'PUMA彪马官方 新款条纹棒球帽 ARCHIVE LOGO 022554 粗呢蓝-条纹-09 均码', 1607241751568);
INSERT INTO `sys_comment` VALUES (18, 1, 38, 8, '阿迪达斯官网adidas 三叶草TEE男装短袖上衣 DV1922 DV1925DV1929 粉蓝 XL(参考身高:188~192CM)', 1610197695733);
INSERT INTO `sys_comment` VALUES (19, 1, 44, 8, '测试测试测试测试测试测试测试测试测试测试测试测试测试', 1610247776515);
INSERT INTO `sys_comment` VALUES (20, 1, 44, 8, '测试测试测试测试测试测试测试测试测试测试测试测试测试', 1610247780833);
INSERT INTO `sys_comment` VALUES (21, 1, 44, 8, '测试测试测试测试测试测试测试测试测试测试测试测试测试', 1610247784508);
INSERT INTO `sys_comment` VALUES (22, 1, 44, 8, '测试测试测试测试测试测试测试测试测试测试测试测试测试', 1610247789686);

-- ----------------------------
-- Table structure for sys_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_goods`;
CREATE TABLE `sys_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/image/user/home/product/15.jpg',
  `price` decimal(10, 2) NOT NULL,
  `state` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1已上架  0未上架',
  `merchant` int(11) NOT NULL COMMENT '商品所属的商户id',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_goods
-- ----------------------------
INSERT INTO `sys_goods` VALUES (11, '耐克NIKE 男子 板鞋 AJ1', '耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码', '/data/goods/5/20201109204752392935.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (12, '耐克NIKE 男子 板鞋 经典', '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码', '/data/goods/5/20201109205306213001.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (13, '休闲鞋 CD5463-200', '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION LOW 休闲鞋 CD5463-200亚麻色44码', '/data/goods/5/20201109205751273390.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (14, '板鞋 AO2810-001', '耐克NIKE 女子 简约 百搭 COURT ROYALE AC 板鞋 AO2810-001黑色36码', '/data/goods/5/20201109205945154276.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (15, '休闲鞋 CZ1055-109', '耐克NIKE 女子 板鞋 经典 BLAZER MID \'77 休闲鞋 CZ1055-109白色36.5码', '/data/goods/5/20201109210325235653.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (17, '运动鞋 CJ0291-005', '耐克NIKE 男子 气垫 跑步鞋 缓震 ZOOM WINFLO 7 运动鞋 CJ0291-005黑色42码', '/data/goods/5/20201109210622705242.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (18, '运动鞋 AV4789-100', '耐克NIKE 男子 休闲鞋 缓震 M2K TEKNO 运动鞋 AV4789-100顶峰白色42码', '/data/goods/5/20201110142709402793.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (19, '运动鞋 AO0269-101', '耐克NIKE 男子 老爹鞋 气垫 ZOOM 2K 运动鞋 AO0269-101白色42.5码', '/data/goods/5/20201110142841106068.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (20, '运动鞋 CK6467-001', '耐克 NIKE 男子 板鞋/复刻鞋 AIR MAX 90 NRG 运动鞋 CK6467-001 黑色 42码', '/data/goods/5/20201110143028570004.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (21, '拖鞋 JORDAN', '耐克 NIKE 中性大童 拖鞋 JORDAN HYDRO XII RETRO BG 运动鞋 820267-107 白色 37.5码', '/data/goods/5/20201110143155960319.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (22, '休闲鞋 NIKE DROP-TYPE', '耐克 NIKE 男子 休闲鞋 NIKE DROP-TYPE MID 运动鞋 BQ5190-300绿 43码', '/data/goods/5/20201110143316976441.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (23, '运动鞋 CZ8681-167', '耐克NIKE 女子 休闲鞋 复古 DAYBREAK 运动鞋 CZ8681-167帆白色38码', '/data/goods/5/20201110143451872085.jpg', 0.01, 1, 5, 1);
INSERT INTO `sys_goods` VALUES (25, '运动外套GE5175', '阿迪达斯官网 adidas neo W ESNTL 3S WB 女装运动外套GE5175 如图 L', '/data/goods/8/20201110214136520879.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (26, '防风衣ED7539', '阿迪达斯官网 adidas 三叶草LOCK UP TT女装防风衣ED7539 ED7541 淡粉紫灰 32(参考身高:160~165CM)', '/data/goods/8/20201110224214151666.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (28, '防风衣 DV0857', '阿迪达斯官网 adidas 三叶草 女装防风衣 DV0857 DW3890 DX3694 浅粉紫 38(参考身高:169~172CM)', '/data/goods/8/20201110224428269386.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (29, '防风衣 ED7217', '阿迪达斯官网 adidas 三叶草 男装防风衣 ED7217 FL1763 黑色 S(参考身高:173~178CM)', '/data/goods/8/20201110224542348334.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (30, '冬季运动羽绒服FL0036', '阿迪达斯官网 adidas 三叶草 DOWN JACKET 女装冬季运动羽绒服FL0036 黑色 34(参考身高:164~167CM)', '/data/goods/8/20201110224729185763.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (31, '运动棉服FJ6523', '阿迪达斯官网adidas 三叶草 女装冬季运动棉服FJ6523 黑色 34(参考身高:164~167CM)', '/data/goods/8/20201110224846847911.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (32, '运动棉服 ED7601', '阿迪达斯官网 adidas 三叶草 LONG BOMBER 女装冬季运动棉服 ED7601 亮光粉 40(参考身高:170~175CM)', '/data/goods/8/20201110225011851415.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (34, '冬季运动棉服GL6407 ', '阿迪达斯官网 adidas 三叶草 Short Sherpa 女装冬季运动棉服GL6407 森林绿/传奇墨水蓝 40(参考身高:170~175CM)', '/data/goods/8/20201110225219159233.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (35, '连帽外套DN8151', '阿迪达斯官网 adidas 三叶草 3STR ZIP HOODIE女装连帽外套DN8151 如图 34', '/data/goods/8/20201110225330615623.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (36, '连帽外套 DV1935', '阿迪达斯官网 adidas 三叶草 FZ HOODY 男装连帽外套 DV1935 绿 XL(参考身高:188~192CM)', '/data/goods/8/20201110225449776961.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (37, '短袖上衣 CE1666', '阿迪达斯官网 adidas 三叶草 女装短袖上衣 CE1666 CE1667 白 32(参考身高:160~165CM)', '/data/goods/8/20201110225601894276.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (38, '短袖上衣 DV1922', '阿迪达斯官网adidas 三叶草TEE男装短袖上衣 DV1922 DV1925DV1929 粉蓝 XL(参考身高:188~192CM)', '/data/goods/8/20201110225703611247.jpg', 0.01, 1, 8, 1);
INSERT INTO `sys_goods` VALUES (39, '棒球帽 ARCHIVE', 'PUMA彪马官方 杨洋同款新款棒球帽 ARCHIVE LOGO LABEL 022778 粉紫色 06 ADULT', '/data/goods/9/20201112192043550007.jpg', 0.01, 1, 9, 0);
INSERT INTO `sys_goods` VALUES (40, '条纹棒球帽 ARCHIVE', 'PUMA彪马官方 新款条纹棒球帽 ARCHIVE LOGO 022554 粗呢蓝-条纹-09 均码', '/data/goods/9/20201112192208777150.jpg', 0.01, 1, 9, 1);
INSERT INTO `sys_goods` VALUES (41, '运动休闲手提包', 'PUMA彪马官方娜扎同款新款女子运动休闲手提包 PRIME PUFFA 078192 银色 02 OSFA/均码', '/data/goods/9/20201112192355215509.jpg', 0.01, 1, 9, 1);
INSERT INTO `sys_goods` VALUES (44, '测试商品(勿拍)', '测试测试测试测试测试测试测试测试测试测试测试测试测试', '/data/goods/8/20210109201101998677.jpg', 0.01, 1, 8, 20);

-- ----------------------------
-- Table structure for sys_mt
-- ----------------------------
DROP TABLE IF EXISTS `sys_mt`;
CREATE TABLE `sys_mt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ratio` float(11, 1) NOT NULL COMMENT '商户每单收费需要支付的费率',
  `state` tinyint(3) NOT NULL COMMENT '1启用 0未启用 2未通过注册',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/lib/merchant/images/2.png',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mt
-- ----------------------------
INSERT INTO `sys_mt` VALUES (5, 'Nike耐克', 'nike@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 1, '/data/header/merchant/5.jpg');
INSERT INTO `sys_mt` VALUES (8, 'Adidas阿迪达斯', 'adidas@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 1, '/data/header/merchant/20201130143514856044.jpg');
INSERT INTO `sys_mt` VALUES (9, 'Puma彪马', 'puma@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 0, '/data/header/merchant/20201130144501417757.jpg');

-- ----------------------------
-- Table structure for sys_mt_ui
-- ----------------------------
DROP TABLE IF EXISTS `sys_mt_ui`;
CREATE TABLE `sys_mt_ui`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int(7) NOT NULL,
  `height` int(7) NOT NULL,
  `merchant` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mt_ui
-- ----------------------------
INSERT INTO `sys_mt_ui` VALUES (5, '/data/mtui/5/20201111171430806651.jpg', 400, 320, 5);
INSERT INTO `sys_mt_ui` VALUES (6, '/data/mtui/5/20201111171436443523.jpg', 600, 310, 5);
INSERT INTO `sys_mt_ui` VALUES (8, '/data/mtui/8/20201111172245637416.jpg', 600, 310, 8);
INSERT INTO `sys_mt_ui` VALUES (9, '/data/mtui/9/20201111174106561114.jpg', 400, 320, 9);
INSERT INTO `sys_mt_ui` VALUES (10, '/data/mtui/9/20201111174116118997.jpg', 600, 310, 9);
INSERT INTO `sys_mt_ui` VALUES (12, '/data/mtui/8/20210109201740252613.jpg', 400, 320, 8);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '0给管理员发送 1 商户  2用户',
  `receive` int(11) NOT NULL COMMENT '接收者的id',
  `create_time` bigint(20) NOT NULL COMMENT '通知发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '测试标题', '测试内容', '0', 1, 1607238236877);
INSERT INTO `sys_notice` VALUES (2, '测试标题', '测试内容', '0', 2, 1607238236897);
INSERT INTO `sys_notice` VALUES (3, '测试标题', '测试内容', '1', 5, 1607238276845);
INSERT INTO `sys_notice` VALUES (4, '测试标题', '测试内容', '1', 8, 1607238276846);
INSERT INTO `sys_notice` VALUES (5, '测试标题', '测试内容', '1', 9, 1607238276847);
INSERT INTO `sys_notice` VALUES (7, '测试标题', '测试内容', '2', 2, 1607238284600);
INSERT INTO `sys_notice` VALUES (8, '测试标题', '测试内容', '2', 3, 1607238284601);
INSERT INTO `sys_notice` VALUES (9, '测试标题', '测试内容', '2', 4, 1607238284601);
INSERT INTO `sys_notice` VALUES (11, '测试标题2', '测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2', '2', 3, 1609926809535);
INSERT INTO `sys_notice` VALUES (12, '测试标题2', '测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2', '2', 4, 1609926809536);
INSERT INTO `sys_notice` VALUES (13, '测试标题2', '测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2', '2', 5, 1609926809536);
INSERT INTO `sys_notice` VALUES (14, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 1, 1610251470774);
INSERT INTO `sys_notice` VALUES (15, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 3, 1610251470775);
INSERT INTO `sys_notice` VALUES (16, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 4, 1610251470776);
INSERT INTO `sys_notice` VALUES (17, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 5, 1610251470776);
INSERT INTO `sys_notice` VALUES (18, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '0', 1, 1610251505029);
INSERT INTO `sys_notice` VALUES (19, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '0', 1, 1610251517513);
INSERT INTO `sys_notice` VALUES (20, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '0', 1, 1610251532490);
INSERT INTO `sys_notice` VALUES (21, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息', '0', 1, 1610251583253);
INSERT INTO `sys_notice` VALUES (22, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息', '2', 1, 1610251604151);
INSERT INTO `sys_notice` VALUES (23, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息', '2', 3, 1610251604152);
INSERT INTO `sys_notice` VALUES (24, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息', '2', 4, 1610251604153);
INSERT INTO `sys_notice` VALUES (25, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息', '2', 5, 1610251604153);
INSERT INTO `sys_notice` VALUES (26, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 1, 1610251616814);
INSERT INTO `sys_notice` VALUES (27, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 3, 1610251616814);
INSERT INTO `sys_notice` VALUES (28, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 4, 1610251616815);
INSERT INTO `sys_notice` VALUES (29, '测试群发系统消息', '测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息测试群发系统消息', '2', 5, 1610251616815);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint(19) NOT NULL,
  `pay_time` bigint(19) NULL DEFAULT NULL,
  `goods_num` int(11) NOT NULL COMMENT '商品数量',
  `order_state` tinyint(3) NOT NULL DEFAULT 0 COMMENT '1已支付  0未支付',
  `order_mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标记',
  `order_user` int(11) NOT NULL COMMENT '用户id',
  `order_merchant` int(11) NOT NULL COMMENT '商户id',
  `order_price` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `goods_id` int(11) NOT NULL,
  `order_notes` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单特殊注释',
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人',
  `user_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `coupon_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠卷代码',
  `pay_way` tinyint(3) NOT NULL COMMENT '1支付宝 0微信支付',
  `pay_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付链接',
  `cart_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批量购买的商品',
  `merchant_ratio` float(11, 1) NOT NULL DEFAULT 1.0 COMMENT '订单创建时商户的费率',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE COMMENT '订单号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES (67, 'EGW202012340213807244213', 1607175487598, 1607175743564, 1, 1, 'AXW202012340213807246245', 1, 5, 0.01, 23, '', '测试', '测试', '测试', '', 0, '/data/pay/AXW202012340213807246245.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (68, 'OXH202012340214627915466', 1607175987049, 1607183449712, 1, 1, 'MYV202012340214627324440', 1, 8, 0.01, 36, '', '测试', '测试', '测试', '', 0, '/data/pay/MYV202012340214627324440.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (69, 'RJF202012340215418785117', 1607176458511, 1607183916993, 1, 1, 'BHL202012340215418546511', 1, 5, 0.01, 19, '', '测试', '测试', '测试', '', 0, '/data/pay/BHL202012340215418546511.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (70, 'OHJ202012340215418723349', 1607176458511, 1607183916993, 1, 1, 'BHL202012340215418546511', 1, 8, 0.01, 34, '', '测试', '测试', '测试', '', 0, '/data/pay/BHL202012340215418546511.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (71, 'TCR202012340221129763691', 1607177489871, 1607183147374, 1, 1, 'ZLA202012340221129912783', 1, 8, 0.01, 34, '', '测试', '测试', '测试', '', 0, '/data/pay/ZLA202012340221129912783.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (72, 'PPI202012340221129185744', 1607177489871, 1607183147374, 1, 1, 'ZLA202012340221129912783', 1, 5, 0.01, 19, '', '测试', '测试', '测试', '', 0, '/data/pay/ZLA202012340221129912783.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (89, 'AVZ202012341002051747406', 1607185251976, 1607185263967, 1, 1, 'KQU202012341002051161208', 1, 9, 0.01, 40, '', '测试', '测试', '测试', '', 0, '/data/pay/KQU202012341002051161208.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (90, 'WVW202012341002151359796', 1607185311336, 1607185323819, 1, 1, 'SDW202012341002151596097', 1, 5, 0.01, 13, '', '测试', '测试', '测试', '', 0, '/data/pay/SDW202012341002151596097.jpg', NULL, 0.1);
INSERT INTO `sys_order` VALUES (91, 'QAK202012341002151746672', 1607185311336, 1607185323819, 1, 1, 'SDW202012341002151596097', 1, 5, 0.01, 19, '', '测试', '测试', '测试', '', 0, '/data/pay/SDW202012341002151596097.jpg', '1', 0.1);
INSERT INTO `sys_order` VALUES (92, 'YYT202012341004300490850', 1607186580573, 1607186595850, 1, 1, 'NFF202012341004300926613', 1, 8, 0.01, 34, '', '测试', '测试', '测试', '', 0, '/data/pay/NFF202012341004300926613.jpg', '13', 0.1);
INSERT INTO `sys_order` VALUES (93, 'LID202012341004300509122', 1607186580573, 1607186595850, 1, 1, 'NFF202012341004300926613', 1, 5, 0.01, 19, '', '测试', '测试', '测试', '', 0, '/data/pay/NFF202012341004300926613.jpg', '10', 0.1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `status` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1已启用 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '未定义角色', 1);
INSERT INTO `sys_role` VALUES (2, '超级管理员', 1);
INSERT INTO `sys_role` VALUES (3, '普通管理员', 1);

-- ----------------------------
-- Table structure for sys_role_access
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_access`;
CREATE TABLE `sys_role_access`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `access_id` int(11) NOT NULL,
  `status` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1启用  0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_access
-- ----------------------------
INSERT INTO `sys_role_access` VALUES (9, 2, 1, 1);
INSERT INTO `sys_role_access` VALUES (10, 2, 2, 1);
INSERT INTO `sys_role_access` VALUES (11, 2, 3, 1);
INSERT INTO `sys_role_access` VALUES (12, 2, 4, 1);
INSERT INTO `sys_role_access` VALUES (13, 2, 5, 1);

-- ----------------------------
-- Table structure for sys_ui
-- ----------------------------
DROP TABLE IF EXISTS `sys_ui`;
CREATE TABLE `sys_ui`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int(7) NOT NULL,
  `height` int(7) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_ui
-- ----------------------------
INSERT INTO `sys_ui` VALUES (2, '/data/library/20201106191900642246.jpg', 1920, 737);
INSERT INTO `sys_ui` VALUES (5, '/data/library/20201106192206806286.jpg', 1230, 535);
INSERT INTO `sys_ui` VALUES (6, '/data/library/20210109210243296422.jpg', 475, 570);
INSERT INTO `sys_ui` VALUES (10, '/data/library/20201130223715934619.jpg', 3151, 282);
INSERT INTO `sys_ui` VALUES (15, '/data/library/20201130222817664532.jpg', 674, 264);
INSERT INTO `sys_ui` VALUES (16, '/data/library/20201201130048784144.jpeg', 3152, 282);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mark` tinyint(3) NOT NULL COMMENT '0未启用  1已启用',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/data/header/user/default.jpg',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'test', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@163.com', 1, '/data/header/user/1.jpg');
INSERT INTO `sys_user` VALUES (3, 'bosen_once@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@qq.com', 1, '/data/header/user/default.jpg');
INSERT INTO `sys_user` VALUES (4, 'test111', 'e10adc3949ba59abbe56e057f20f883e', '806317173@qq.com', 0, '/data/header/user/default.jpg');
INSERT INTO `sys_user` VALUES (5, 'lalalal', 'e10adc3949ba59abbe56e057f20f883e', '2390025288@qq.com', 0, '/data/header/user/default.jpg');

SET FOREIGN_KEY_CHECKS = 1;
