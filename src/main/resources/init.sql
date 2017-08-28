/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : springboot-mult-modules
Target Host     : localhost:3306
Target Database : springboot-mult-modules
Date: 2017-08-28 16:20:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `original_file_name` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('f016939346324dca8cb677e3ea64e74a', 'QQ截图20170821133931.png', '.png', '175840', 'image/png', '85c1feaff9b34e15aaf2970abf068c90');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父ID',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '树ID',
  `menuName` varchar(100) NOT NULL COMMENT '菜单名称',
  `sort` decimal(10,0) DEFAULT '1' COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `show` char(1) DEFAULT '1' COMMENT '是否显示\n1：显示\n0：隐藏',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记\n1：删除\n0：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '', null, '首页', '1', null, 'glyphicon glyphicon-dashboard', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('2', '', null, '系统管理', '1', null, 'fa fa-desktop', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('20', '2', '', '用户管理', '2', '/api/user/list', 'fa fa-user', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('21', '2', null, '角色管理', '2', '/api/role/list', 'fa fa-user', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('22', '2', null, '菜单管理', '2', '/api/menu/list', 'fa fa-th-list', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('23', '2', null, '日志管理', '2', null, 'fa fa-file-text', '1', null, null, null, null, '0');
INSERT INTO `sys_menu` VALUES ('24', '2', null, '文件管理', '1', '/api/file/list', 'fa fa-folder', '1', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `roleName` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '1', '管理员', null, null);
INSERT INTO `sys_role` VALUES ('123', 'ROLE_TEST', '0', 'This is ROLE_TEST', '2017-08-18 00:00:00', '2017-08-18 00:00:00');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '1', '普通用户', null, '2017-08-18 00:00:00');
INSERT INTO `sys_role` VALUES ('a1', 'ROLE_1', '1', '角色1', null, null);
INSERT INTO `sys_role` VALUES ('a2', 'ROLE_2', '1', '角色2', null, null);
INSERT INTO `sys_role` VALUES ('a3', 'ROLE_3', '1', '角色3', null, null);
INSERT INTO `sys_role` VALUES ('a4', 'ROLE_4', '1', '角色4', null, null);
INSERT INTO `sys_role` VALUES ('a5', 'ROLE_5', '1', '角色5', null, null);
INSERT INTO `sys_role` VALUES ('a6', 'ROLE_6', '1', '角色6', null, null);
INSERT INTO `sys_role` VALUES ('a7', 'ROLE_7', '1', '角色7', null, null);
INSERT INTO `sys_role` VALUES ('a8', 'ROLE_8', '1', '角色8', null, null);
INSERT INTO `sys_role` VALUES ('a9', 'ROLE_9', '1', '角色9', null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(255) NOT NULL DEFAULT '',
  `menu_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('2', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(36) NOT NULL COMMENT '编号',
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL DEFAULT '123' COMMENT '密码',
  `icon` varchar(255) DEFAULT NULL COMMENT '图像',
  `userNameCn` varchar(100) DEFAULT NULL COMMENT '中文名',
  `userNameEn` varchar(100) DEFAULT NULL COMMENT '英文名',
  `enabled` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用\n1：可用\n0：停用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记\n1：删除\n0：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123', null, '超级管理员', 'administrator', '1', '超级管理员', null, null, '0');
INSERT INTO `sys_user` VALUES ('10', 'admin10', '123', null, '普通用户10', 'administrator', '0', '普通用户10', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('11', 'admin11', '123', null, '普通用户11', 'administrator', '0', '普通用户11', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '1');
INSERT INTO `sys_user` VALUES ('12', 'admin12', '123', null, '普通用户12', 'administrator', '1', '普通用户12', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '1');
INSERT INTO `sys_user` VALUES ('2', 'admin2', '123', null, '普通用户2', 'administrator', '1', '普通用户2', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('3', 'admin3', '123', null, '普通用户3', 'administrator', '1', '普通用户3', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('4', 'admin4', '123', null, '普通用户4', 'administrator', '1', '普通用户4', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('5', 'admin5', '123', null, '普通用户5', 'administrator', '1', '普通用户5', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('6', 'admin6', '123', null, '普通用户6', 'administrator', '1', '普通用户6', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('7', 'admin7', '123', null, '普通用户7', 'administrator', '1', '普通用户7', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('8', 'admin8', '123', null, '普通用户8', 'administrator', '0', '普通用户8', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('85c1feaff9b34e15aaf2970abf068c90', '2222', '123', null, '1111', '3333', '0', '', '2017-08-24 14:34:55', null, '0');
INSERT INTO `sys_user` VALUES ('9', 'admin9', '123', null, '普通用户9', 'administrator', '0', '普通用户9', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(255) NOT NULL DEFAULT '',
  `role_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('0ba32f991341480a848272929ed4b0da', '');
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('7d5441e1bcff454ab4695faaeb19ef11', 'a7');
INSERT INTO `sys_user_role` VALUES ('85c1feaff9b34e15aaf2970abf068c90', 'a9');
