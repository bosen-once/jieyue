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

 Date: 06/12/2021 21:17:08
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES (1, 'bosen', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@163.com', 1);
INSERT INTO `sys_admin` VALUES (2, '2390025289@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '2390025289@qq.com', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cart
-- ----------------------------
INSERT INTO `sys_cart` VALUES (4, 40, 2, 1);
INSERT INTO `sys_cart` VALUES (5, 39, 2, 1);
INSERT INTO `sys_cart` VALUES (7, 18, 2, 1);
INSERT INTO `sys_cart` VALUES (10, 70, 1, 1);
INSERT INTO `sys_cart` VALUES (11, 51, 1, 1);
INSERT INTO `sys_cart` VALUES (12, 73, 1, 1);
INSERT INTO `sys_cart` VALUES (13, 47, 1, 1);

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
  `create_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_comment
-- ----------------------------
INSERT INTO `sys_comment` VALUES (25, 1, 12, 5, '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码', 1615988609112);
INSERT INTO `sys_comment` VALUES (26, 1, 12, 5, '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码', 1615988693324);
INSERT INTO `sys_comment` VALUES (27, 1, 12, 5, '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码', 1615988697577);
INSERT INTO `sys_comment` VALUES (28, 1, 11, 5, '耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码\r\n', 1616727058835);
INSERT INTO `sys_comment` VALUES (29, 1, 11, 5, '耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码\r\n', 1616727061866);
INSERT INTO `sys_comment` VALUES (30, 1, 11, 5, '耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44', 1616727068620);
INSERT INTO `sys_comment` VALUES (31, 1, 44, 8, '测试商品！！！！勿拍', 1616738590017);
INSERT INTO `sys_comment` VALUES (32, 1, 44, 8, '测试商品！！！！勿拍', 1616738593903);
INSERT INTO `sys_comment` VALUES (33, 1, 44, 8, '测试商品！！！！勿拍', 1616738597935);
INSERT INTO `sys_comment` VALUES (34, 1, 45, 13, '回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42', 1617111760695);
INSERT INTO `sys_comment` VALUES (35, 1, 45, 13, '回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42', 1617111764783);
INSERT INTO `sys_comment` VALUES (36, 1, 45, 13, '回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42', 1617111768764);
INSERT INTO `sys_comment` VALUES (37, 1, 45, 13, '回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42', 1617111781185);
INSERT INTO `sys_comment` VALUES (38, 1, 46, 13, '回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42', 1617111806109);
INSERT INTO `sys_comment` VALUES (39, 1, 46, 13, '回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42', 1617111809179);
INSERT INTO `sys_comment` VALUES (40, 1, 46, 13, '回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42', 1617111812815);
INSERT INTO `sys_comment` VALUES (41, 1, 47, 13, '回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39', 1617111825697);
INSERT INTO `sys_comment` VALUES (42, 1, 47, 13, '回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39', 1617111828717);
INSERT INTO `sys_comment` VALUES (43, 1, 47, 13, '回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39', 1617111832251);
INSERT INTO `sys_comment` VALUES (44, 1, 48, 13, '回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35', 1617111850625);
INSERT INTO `sys_comment` VALUES (45, 1, 48, 13, '回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35', 1617111853711);
INSERT INTO `sys_comment` VALUES (46, 1, 48, 13, '回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35', 1617111857226);
INSERT INTO `sys_comment` VALUES (47, 1, 74, 11, '这是一只用于测试的海绵宝宝，勿拍！！！', 1617113112104);
INSERT INTO `sys_comment` VALUES (48, 1, 74, 11, '这是一只用于测试的海绵宝宝，勿拍！！！', 1617113115302);
INSERT INTO `sys_comment` VALUES (49, 1, 74, 11, '这是一只用于测试的海绵宝宝，勿拍！！！', 1617113118469);
INSERT INTO `sys_comment` VALUES (50, 1, 73, 14, '安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)', 1617113128909);
INSERT INTO `sys_comment` VALUES (51, 1, 73, 14, '安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)', 1617113131491);
INSERT INTO `sys_comment` VALUES (52, 1, 73, 14, '安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)', 1617113135673);
INSERT INTO `sys_comment` VALUES (53, 1, 72, 14, '安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)', 1617113146086);
INSERT INTO `sys_comment` VALUES (54, 1, 72, 14, '安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)', 1617113148955);
INSERT INTO `sys_comment` VALUES (55, 1, 72, 14, '安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)', 1617113152297);
INSERT INTO `sys_comment` VALUES (56, 1, 71, 14, '安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)', 1617113164057);
INSERT INTO `sys_comment` VALUES (57, 1, 71, 14, '安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)', 1617113166989);
INSERT INTO `sys_comment` VALUES (58, 1, 71, 14, '安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)', 1617113169875);
INSERT INTO `sys_comment` VALUES (59, 1, 70, 12, '李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5', 1618918721874);
INSERT INTO `sys_comment` VALUES (60, 1, 70, 12, '李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5', 1618918725838);

-- ----------------------------
-- Table structure for sys_goods
-- ----------------------------
DROP TABLE IF EXISTS `sys_goods`;
CREATE TABLE `sys_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/image/goods-default.jpg',
  `price` decimal(10, 2) NOT NULL,
  `state` tinyint(3) NOT NULL DEFAULT 1 COMMENT '1已上架  0未上架',
  `merchant` int(11) NOT NULL COMMENT '商品所属的商户id',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_goods
-- ----------------------------
INSERT INTO `sys_goods` VALUES (11, '耐克NIKE 男子 板鞋 AJ1', '耐克NIKE 男子 板鞋 AJ1 乔1 AIR JORDAN 1 LOW SE 休闲鞋 CK3022-005黑色44码', '/data/goods/5/20201109204752392935.jpg', 0.01, 0, 5, 0);
INSERT INTO `sys_goods` VALUES (12, '耐克NIKE 男子 板鞋 经典', '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION MID 休闲鞋 CD5466-101白色42码', '/data/goods/5/20201109205306213001.jpg', 0.01, 0, 5, 0);
INSERT INTO `sys_goods` VALUES (13, '休闲鞋 CD5463-200', '耐克NIKE 男子 板鞋 经典 板鞋 COURT VISION LOW 休闲鞋 CD5463-200亚麻色44码', '/data/goods/5/20201109205751273390.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (14, '板鞋 AO2810-001', '耐克NIKE 女子 简约 百搭 COURT ROYALE AC 板鞋 AO2810-001黑色36码', '/data/goods/5/20201109205945154276.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (15, '休闲鞋 CZ1055-109', '耐克NIKE 女子 板鞋 经典 BLAZER MID \'77 休闲鞋 CZ1055-109白色36.5码', '/data/goods/5/20201109210325235653.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (17, '运动鞋 CJ0291-005', '耐克NIKE 男子 气垫 跑步鞋 缓震 ZOOM WINFLO 7 运动鞋 CJ0291-005黑色42码', '/data/goods/5/20201109210622705242.jpg', 0.01, 0, 5, 0);
INSERT INTO `sys_goods` VALUES (18, '运动鞋 AV4789-100', '耐克NIKE 男子 休闲鞋 缓震 M2K TEKNO 运动鞋 AV4789-100顶峰白色42码', '/data/goods/5/20201110142709402793.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (19, '运动鞋 AO0269-101', '耐克NIKE 男子 老爹鞋 气垫 ZOOM 2K 运动鞋 AO0269-101白色42.5码', '/data/goods/5/20201110142841106068.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (20, '运动鞋 CK6467-001', '耐克 NIKE 男子 板鞋/复刻鞋 AIR MAX 90 NRG 运动鞋 CK6467-001 黑色 42码', '/data/goods/5/20201110143028570004.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (21, '拖鞋 JORDAN', '耐克 NIKE 中性大童 拖鞋 JORDAN HYDRO XII RETRO BG 运动鞋 820267-107 白色 37.5码', '/data/goods/5/20201110143155960319.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (22, '休闲鞋 NIKE DROP-TYPE', '耐克 NIKE 男子 休闲鞋 NIKE DROP-TYPE MID 运动鞋 BQ5190-300绿 43码', '/data/goods/5/20201110143316976441.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (23, '运动鞋 CZ8681-167', '耐克NIKE 女子 休闲鞋 复古 DAYBREAK 运动鞋 CZ8681-167帆白色38码', '/data/goods/5/20201110143451872085.jpg', 0.01, 0, 5, 1);
INSERT INTO `sys_goods` VALUES (25, '运动外套GE5175', '阿迪达斯官网 adidas neo W ESNTL 3S WB 女装运动外套GE5175 如图 L', '/data/goods/8/20201110214136520879.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (26, '防风衣ED7539', '阿迪达斯官网 adidas 三叶草LOCK UP TT女装防风衣ED7539 ED7541 淡粉紫灰 32(参考身高:160~165CM)', '/data/goods/8/20201110224214151666.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (28, '防风衣 DV0857', '阿迪达斯官网 adidas 三叶草 女装防风衣 DV0857 DW3890 DX3694 浅粉紫 38(参考身高:169~172CM)', '/data/goods/8/20201110224428269386.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (29, '防风衣 ED7217', '阿迪达斯官网 adidas 三叶草 男装防风衣 ED7217 FL1763 黑色 S(参考身高:173~178CM)', '/data/goods/8/20201110224542348334.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (30, '冬季运动羽绒服FL0036', '阿迪达斯官网 adidas 三叶草 DOWN JACKET 女装冬季运动羽绒服FL0036 黑色 34(参考身高:164~167CM)', '/data/goods/8/20201110224729185763.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (31, '运动棉服FJ6523', '阿迪达斯官网adidas 三叶草 女装冬季运动棉服FJ6523 黑色 34(参考身高:164~167CM)', '/data/goods/8/20201110224846847911.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (32, '运动棉服 ED7601', '阿迪达斯官网 adidas 三叶草 LONG BOMBER 女装冬季运动棉服 ED7601 亮光粉 40(参考身高:170~175CM)', '/data/goods/8/20201110225011851415.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (34, '冬季运动棉服GL6407 ', '阿迪达斯官网 adidas 三叶草 Short Sherpa 女装冬季运动棉服GL6407 森林绿/传奇墨水蓝 40(参考身高:170~175CM)', '/data/goods/8/20201110225219159233.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (35, '连帽外套DN8151', '阿迪达斯官网 adidas 三叶草 3STR ZIP HOODIE女装连帽外套DN8151 如图 34', '/data/goods/8/20201110225330615623.jpg', 0.01, 0, 8, 0);
INSERT INTO `sys_goods` VALUES (36, '连帽外套 DV1935', '阿迪达斯官网 adidas 三叶草 FZ HOODY 男装连帽外套 DV1935 绿 XL(参考身高:188~192CM)', '/data/goods/8/20201110225449776961.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (37, '短袖上衣 CE1666', '阿迪达斯官网 adidas 三叶草 女装短袖上衣 CE1666 CE1667 白 32(参考身高:160~165CM)', '/data/goods/8/20201110225601894276.jpg', 0.01, 0, 8, 1);
INSERT INTO `sys_goods` VALUES (38, '短袖上衣 DV1922', '阿迪达斯官网adidas 三叶草TEE男装短袖上衣 DV1922 DV1925DV1929 粉蓝 XL(参考身高:188~192CM)', '/data/goods/8/20201110225703611247.jpg', 0.01, 0, 8, 0);
INSERT INTO `sys_goods` VALUES (39, '棒球帽 ARCHIVE', 'PUMA彪马官方 杨洋同款新款棒球帽 ARCHIVE LOGO LABEL 022778 粉紫色 06 ADULT', '/data/goods/9/20201112192043550007.jpg', 0.01, 0, 9, 0);
INSERT INTO `sys_goods` VALUES (40, '条纹棒球帽 ARCHIVE', 'PUMA彪马官方 新款条纹棒球帽 ARCHIVE LOGO 022554 粗呢蓝-条纹-09 均码', '/data/goods/9/20201112192208777150.jpg', 0.01, 0, 9, 0);
INSERT INTO `sys_goods` VALUES (41, '运动休闲手提包', 'PUMA彪马官方娜扎同款新款女子运动休闲手提包 PRIME PUFFA 078192 银色 02 OSFA/均码', '/data/goods/9/20201112192355215509.jpg', 0.01, 0, 9, 0);
INSERT INTO `sys_goods` VALUES (44, '测试商品(勿拍)', '测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)；测试商品(勿拍)测试商品(勿拍)！！！', '/data/goods/8/20210109201101998677.jpg', 0.01, 0, 8, 82);
INSERT INTO `sys_goods` VALUES (45, '国潮手绘皮卡丘帆布鞋', '回力官方旗舰 国潮手绘皮卡丘帆布鞋高帮男鞋联名爆改涂鸦鞋子2021新款春季潮流板鞋 黄色闪电（五天内发货） 42', '/data/goods/13/20210330212031740396.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (46, ' 国潮手绘高帮帆布鞋', '回力官方旗舰 国潮手绘高帮帆布鞋女士2021新款春季樱花鞋爆改休闲鞋男 缤纷樱花473SH（五天内发货） 42', '/data/goods/13/20210330212214866150.jpg', 0.01, 1, 13, 8);
INSERT INTO `sys_goods` VALUES (47, '本命年高帮帆布鞋', '回力官方旗舰 本命年高帮帆布鞋男鞋嘻哈透气男女休闲鞋潮流百搭学生情侣鞋子【国潮系列】 花布WXY-A363T 39', '/data/goods/13/20210330212245424549.jpg', 0.01, 1, 13, 8);
INSERT INTO `sys_goods` VALUES (48, '板鞋女鞋2021秋冬季新', '回力官方旗舰 板鞋女鞋2021秋冬季新款马卡龙撞色低帮透气百搭休闲运动小白鞋子女 白糖果WXYA562C 35', '/data/goods/13/20210330212317794584.jpg', 0.01, 1, 13, 8);
INSERT INTO `sys_goods` VALUES (49, '国潮手绘星球宇航员帆布鞋', '回力官方旗舰 国潮手绘星球宇航员帆布鞋情侣2021春夏季新款爆改NASA高帮女士板鞋男鞋子潮鞋子 星球宇航员（下单后五天内发货） 39', '/data/goods/13/20210330212343230193.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (50, '国潮手绘彩虹天使马帆布鞋', '回力官方旗舰 国潮手绘彩虹天使马帆布鞋男鞋子2021春夏季女士新款爆改手绘独角兽高帮板鞋潮鞋布鞋 彩虹天使马（下单后五天内发货） 37', '/data/goods/13/20210330212408614514.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (51, 'ins潮复古老爹鞋', '回力官方旗舰 跑步鞋男鞋2021秋季新款ins潮复古老爹鞋学生透气网面减震休闲运动鞋子男 军绿WXY0170 42', '/data/goods/13/20210330212444207204.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (52, '荧光手绘帆布鞋星际宇航员高帮鞋', '回力官方旗舰 荧光手绘帆布鞋星际宇航员高帮鞋2021新款春季男女鞋潮流休闲鞋国潮板鞋子男 夜光黑色星球宇航员 42', '/data/goods/13/20210330212516740293.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (53, '国潮手绘恶魔小丑高帮帆布鞋', '【手绘】回力官方旗舰 国潮手绘恶魔小丑高帮帆布鞋樱花爆改恶魔系情侣休闲鞋男女鞋涂鸦潮鞋子 恶魔小丑（下单后五天内发货） 42', '/data/goods/13/20210330212538705519.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (54, '国潮手绘浮世绘仙鹤鲸鱼帆布鞋', '【手绘】回力官方旗舰 国潮手绘浮世绘仙鹤鲸鱼帆布鞋男鞋手绘2021春夏季女爆改涂鸦中国风布鞋子 浮世绘仙鹤鲸鱼（下单后五天内发货） 42', '/data/goods/13/20210330212601581309.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (55, '小雏菊经典情侣高帮', '回力官方旗舰 小雏菊经典情侣高帮帆布休闲鞋男正品板鞋手绘印刷新款女高帮鞋子 黑色花(女生建议选小一码) 35', '/data/goods/13/20210330212628919032.jpg', 0.01, 1, 13, 9);
INSERT INTO `sys_goods` VALUES (56, '国潮蓝手绘经典款帆布鞋', '回力官方旗舰 国潮蓝手绘经典款帆布鞋高帮男女2021春季新款情侣国潮红百搭休闲爆改涂鸦鞋子 国潮蓝（下单后五天内发货） 41', '/data/goods/13/20210330212653590038.jpg', 0.01, 1, 13, 10);
INSERT INTO `sys_goods` VALUES (57, '运动裤卫裤', '李宁运动裤卫裤女官方旗舰网运动时尚系列女子收口卫裤休闲长裤时尚 黑色A-5 M', '/data/goods/12/20210330213038709716.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (59, 'T恤短袖女夏季新品', '李宁T恤短袖女夏季新品女子休闲运动短袖T恤青年薄款休闲跑步健身服圆领休闲上衣短袖官方旗舰网 浅桃粉-3 M', '/data/goods/12/20210330213112628569.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (60, '女子减震休闲鞋', '李宁女鞋运动鞋女子减震休闲鞋2021年新品低帮舒适减震透气运动鞋学生户外休闲鞋官方旗舰网 云雾白-1 37', '/data/goods/12/20210330213147125049.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (61, '一体织反光速干凉爽短袖T恤', '李宁短袖T恤2021春夏新品男子一体织反光速干凉爽短袖T恤男跑步系列健身训练修身短袖官方旗舰网 灰水蓝标准白-5 L', '/data/goods/12/20210330213233328992.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (62, '运动短裤男裤2021春夏新品', '李宁运动短裤男裤2021春夏新品男透气五分裤训练系列夏季短裤经典多色两侧拉链口袋休闲短裤官方旗舰网 黑色/绿-4 L', '/data/goods/12/20210330213254176860.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (63, '运动短裤男裤2021夏季新品宽松透气', '李宁运动短裤男裤2021夏季新品宽松透气五分裤男宽松短卫裤男士透气舒适宽松运动裤官方旗舰网 黑色-1 L', '/data/goods/12/20210330213319617195.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (64, '男子棉舒适印花短袖', '李宁短袖T恤2021夏季新品男子棉舒适印花短袖文化衫男吸湿透气短袖星球大战联名系列男装官方旗舰网 黑色/B-6 L', '/data/goods/12/20210330213355751756.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (66, '星球大战联名系列男子宽松短袖', '李宁短袖T恤男2021新品星球大战联名系列男子宽松短袖文化衫官方旗舰网AHSR407 黑色-4 L', '/data/goods/12/20210330213446627820.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (67, '超轻17男子轻质透气网面运动鞋', '李宁男鞋跑步鞋超轻17男子轻质透气网面运动鞋舒适耐磨防滑旅游鞋官方旗舰网 藏青蓝/民谣蓝（男款）ARBQ003-5 42', '/data/goods/12/20210330213532368165.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (68, '低帮运动鞋新款情侣小白鞋', '李宁女鞋板鞋低帮运动鞋新款情侣小白鞋经典减震防滑耐磨休闲鞋时尚学生滑板鞋帆布鞋型 云雾白-8 37', '/data/goods/12/20210330213601133941.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (69, '春夏新品舒适减震跑鞋', '李宁䨻beng超轻18女子2021春夏新品舒适减震跑鞋学生轻质透气经典百搭运动鞋旅游鞋女鞋官方旗舰网 标准白/荧光橙橘-7 38', '/data/goods/12/20210330213633953914.jpg', 0.01, 1, 12, 10);
INSERT INTO `sys_goods` VALUES (70, '拖鞋女鞋夏季新品', '李宁拖鞋女鞋夏季新品Disney迪士尼米奇联名款女子轻便情侣鞋条纹魔术贴凉鞋官方旗舰网 标准白/标准黑-3 37.5', '/data/goods/12/20210330213652173582.jpg', 0.01, 1, 12, 9);
INSERT INTO `sys_goods` VALUES (71, '安踏官方旗舰老爹鞋女鞋', '安踏官方旗舰老爹鞋女鞋2021春季新款ins时尚休闲运动鞋女潮鞋子女士休闲鞋男鞋情侣鞋 安踏白/烟雾紫-8 6.5(女37.5)', '/data/goods/14/20210330213748876483.jpg', 0.01, 1, 14, 8);
INSERT INTO `sys_goods` VALUES (72, '安踏脉冲2代男鞋', '安踏脉冲2代男鞋男2021春季新款男士户外休闲鞋旅游鞋官方旗舰网店912118852 浅米白/墨水蓝-1 8.5(男42)', '/data/goods/14/20210330213810878030.jpg', 0.01, 1, 14, 9);
INSERT INTO `sys_goods` VALUES (73, '安踏女休闲鞋2021春季新款', '安踏女休闲鞋2021春季新款轻便跑步鞋子潮流时尚女鞋老爹鞋女运动鞋子百搭猫爪鞋官方旗舰网店 象牙白/迷雾紫/浅雾灰-5 6.5(女37.5)', '/data/goods/14/20210330213829639750.jpg', 0.01, 1, 14, 10);
INSERT INTO `sys_goods` VALUES (74, '测试商品（勿拍）', '这是一只用于测试的海绵宝宝，勿拍！！！', '/data/goods/11/20210330220346963836.jpg', 0.01, 0, 11, 4);

-- ----------------------------
-- Table structure for sys_mt
-- ----------------------------
DROP TABLE IF EXISTS `sys_mt`;
CREATE TABLE `sys_mt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ratio` float(11, 1) NOT NULL DEFAULT 1.0 COMMENT '商户每单收费需要支付的费率',
  `state` tinyint(3) NOT NULL DEFAULT 0 COMMENT '1启用 0未启用 2未通过注册',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/data/header/merchant/default.png',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mt
-- ----------------------------
INSERT INTO `sys_mt` VALUES (5, 'Nike耐克', 'nike@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1.0, 0, '/data/header/merchant/5/20210322192948971290.jpg');
INSERT INTO `sys_mt` VALUES (8, 'Adidas阿迪达斯', 'adidas@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1.0, 0, '/data/header/merchant/8/20210322193005818751.jpg');
INSERT INTO `sys_mt` VALUES (9, 'Puma彪马', 'puma@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 1.0, 0, '/data/header/merchant/9/20210322193031653785.jpg');
INSERT INTO `sys_mt` VALUES (11, 'TEST测试商户', 'bosen_once@163.com', 'e10adc3949ba59abbe56e057f20f883e', 1.0, 1, '/data/header/merchant/11/20210330220121874813.jpeg');
INSERT INTO `sys_mt` VALUES (12, 'LI-NING李宁', 'lining@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 1, '/data/header/merchant/12/20210330170610424273.jpeg');
INSERT INTO `sys_mt` VALUES (13, 'WARRIOR回力', 'warrior@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 1, '/data/header/merchant/13/20210330170639894124.jpeg');
INSERT INTO `sys_mt` VALUES (14, 'ANTA安踏', 'anta@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0.1, 1, '/data/header/merchant/14/20210330170723131771.jpeg');

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_mt_ui
-- ----------------------------
INSERT INTO `sys_mt_ui` VALUES (5, '/data/mtui/5/20201111171430806651.jpg', 400, 320, 5);
INSERT INTO `sys_mt_ui` VALUES (6, '/data/mtui/5/20201111171436443523.jpg', 600, 310, 5);
INSERT INTO `sys_mt_ui` VALUES (8, '/data/mtui/8/20201111172245637416.jpg', 600, 310, 8);
INSERT INTO `sys_mt_ui` VALUES (9, '/data/mtui/9/20201111174106561114.jpg', 400, 320, 9);
INSERT INTO `sys_mt_ui` VALUES (10, '/data/mtui/9/20201111174116118997.jpg', 600, 310, 9);
INSERT INTO `sys_mt_ui` VALUES (12, '/data/mtui/8/20210109201740252613.jpg', 400, 320, 8);
INSERT INTO `sys_mt_ui` VALUES (13, '/data/mtui/12/20210330191353774645.jpg', 400, 320, 12);
INSERT INTO `sys_mt_ui` VALUES (14, '/data/mtui/12/20210330191411134816.jpeg', 600, 310, 12);
INSERT INTO `sys_mt_ui` VALUES (15, '/data/mtui/13/20210330191433420565.jpg', 400, 320, 13);
INSERT INTO `sys_mt_ui` VALUES (16, '/data/mtui/13/20210330191441571457.jpg', 600, 310, 13);
INSERT INTO `sys_mt_ui` VALUES (17, '/data/mtui/14/20210330191733928362.jpg', 400, 320, 14);
INSERT INTO `sys_mt_ui` VALUES (18, '/data/mtui/14/20210330191638457952.jpg', 600, 310, 14);
INSERT INTO `sys_mt_ui` VALUES (19, '/data/mtui/11/20210807190318252283.jpg', 400, 320, 11);
INSERT INTO `sys_mt_ui` VALUES (20, '/data/mtui/11/20210807190324130420.jpg', 600, 310, 11);

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
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_order` VALUES (101, 'GKJ202112340211010176213', 1638796210886, 1638796384928, 1, 1, 'GAU202112340211010908695', 1, 13, 0.01, 45, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/11f3333f-6d68-4adb-9cf6-035a39a4a551.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (102, 'ZSS202112340211024283551', 1638796224161, 1638796384928, 1, 1, 'ELN202112340211024324951', 1, 13, 0.01, 46, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/147fb8b1-18ac-480a-8d57-2c21635d9519.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (103, 'ZDK202112340211037495045', 1638796237222, 1638796384928, 1, 1, 'IJD202112340211037974203', 1, 13, 0.01, 47, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/4801e190-5640-4f14-a593-66029aa2d0ea.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (104, 'UWU202112340211051689881', 1638796251864, 1638796384928, 1, 1, 'NTP202112340211051560839', 1, 13, 0.01, 48, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/896bd6fa-e609-405c-b601-caa56d71659f.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (105, 'LAW202112340211111244316', 1638796271424, 1638796384928, 1, 1, 'EVM202112340211111457150', 1, 13, 0.01, 49, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/814b7cf6-3e81-4356-aa27-023bf2eb0609.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (106, 'QZG202112340211127932176', 1638796287661, 1638796384928, 1, 1, 'CKX202112340211127838508', 1, 13, 0.01, 50, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/2a3ecc4c-62e4-4964-aa75-1c2a3d082ba1.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (107, 'MVF202112340211155962656', 1638796315927, 1638796384928, 1, 1, 'GXI202112340211155762974', 1, 13, 0.01, 51, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/175a4dc6-1077-46ef-b64e-0e2a9ae66ee0.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (108, 'GYV202112340211210918392', 1638796330108, 1638796384928, 1, 1, 'NJY202112340211210411460', 1, 13, 0.01, 52, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/b7e61e0a-fea6-4022-b9bb-a3949220c085.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (109, 'XAB202112340211229316121', 1638796349401, 1638796384928, 1, 1, 'BGA202112340211229992111', 1, 13, 0.01, 53, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/9d358e5a-638e-4437-8a00-d9f942e5f58f.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (110, 'YUL202112340211250396901', 1638796370482, 1638796384928, 1, 1, 'OAX202112340211250369042', 1, 13, 0.01, 54, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/3db2f150-1b6d-41fa-8b6d-eca59c5f39cc.jpg', NULL, 0.0);
INSERT INTO `sys_order` VALUES (111, 'BHY202112340211304317807', 1638796384928, 1638796384928, 1, 1, 'ETZ202112340211304214293', 1, 13, 0.01, 55, '', 'xxxxxxxxxxxxx', 'Bosen', '123456', '', 0, 'data/pay/87b2e60d-464c-499a-90be-b93e1ea58911.jpg', NULL, 0.0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_ui
-- ----------------------------
INSERT INTO `sys_ui` VALUES (18, '/data/library/20210328110354280473.jpg', 1230, 535);
INSERT INTO `sys_ui` VALUES (19, '/data/library/20210330181647732986.jpg', 3151, 282);
INSERT INTO `sys_ui` VALUES (20, '/data/library/20210330181628569458.jpeg', 3152, 282);
INSERT INTO `sys_ui` VALUES (21, '/data/library/20210330190628805198.jpeg', 475, 570);
INSERT INTO `sys_ui` VALUES (22, '/data/library/20210330185815215536.jpg', 674, 264);
INSERT INTO `sys_ui` VALUES (23, '/data/library/20210322125308458944.jpg', 1920, 737);

-- ----------------------------
-- Table structure for sys_ui_copy1
-- ----------------------------
DROP TABLE IF EXISTS `sys_ui_copy1`;
CREATE TABLE `sys_ui_copy1`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int(7) NOT NULL,
  `height` int(7) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_ui_copy1
-- ----------------------------
INSERT INTO `sys_ui_copy1` VALUES (18, '/data/library/20210328110354280473.jpg', 1230, 535);
INSERT INTO `sys_ui_copy1` VALUES (19, '/data/library/20210330181647732986.jpg', 3151, 282);
INSERT INTO `sys_ui_copy1` VALUES (20, '/data/library/20210330181628569458.jpeg', 3152, 282);
INSERT INTO `sys_ui_copy1` VALUES (21, '/data/library/20210330190628805198.jpeg', 475, 570);
INSERT INTO `sys_ui_copy1` VALUES (22, '/data/library/20210330185815215536.jpg', 674, 264);
INSERT INTO `sys_ui_copy1` VALUES (23, '/data/library/20210322125308458944.jpg', 1920, 737);

-- ----------------------------
-- Table structure for sys_ui_copy2
-- ----------------------------
DROP TABLE IF EXISTS `sys_ui_copy2`;
CREATE TABLE `sys_ui_copy2`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `width` int(7) NOT NULL,
  `height` int(7) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_ui_copy2
-- ----------------------------
INSERT INTO `sys_ui_copy2` VALUES (18, '/data/library/20210328110354280473.jpg', 1230, 535);
INSERT INTO `sys_ui_copy2` VALUES (19, '/data/library/20210330181647732986.jpg', 3151, 282);
INSERT INTO `sys_ui_copy2` VALUES (20, '/data/library/20210330181628569458.jpeg', 3152, 282);
INSERT INTO `sys_ui_copy2` VALUES (21, '/data/library/20210330190628805198.jpeg', 475, 570);
INSERT INTO `sys_ui_copy2` VALUES (22, '/data/library/20210330185815215536.jpg', 674, 264);
INSERT INTO `sys_ui_copy2` VALUES (23, '/data/library/20210322125308458944.jpg', 1920, 737);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'Bosen', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@163.com', 1, '/data/header/user/1.jpg');
INSERT INTO `sys_user` VALUES (3, 'bosen_once@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'bosen_once@qq.com', 1, '/data/header/user/default.jpg');
INSERT INTO `sys_user` VALUES (4, 'test111', 'e10adc3949ba59abbe56e057f20f883e', '806317173@qq.com', 0, '/data/header/user/default.jpg');
INSERT INTO `sys_user` VALUES (5, 'lalalal', 'e10adc3949ba59abbe56e057f20f883e', '2390025288@qq.com', 0, '/data/header/user/default.jpg');
INSERT INTO `sys_user` VALUES (7, '2390025289@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '2390025289@qq.com', 1, '/data/header/user/default.jpg');

SET FOREIGN_KEY_CHECKS = 1;
