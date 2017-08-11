-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `username` VARCHAR(100) NOT NULL COMMENT '登录名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `userNameCn` VARCHAR(100) NOT NULL COMMENT '中文名',
  `userNameEn` VARCHAR(100) NOT NULL COMMENT '英文名',
  `enabled` CHAR(1) NOT NULL DEFAULT '1' COMMENT '是否可用\n1：可用\n0：停用',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_date` DATETIME NULL COMMENT '创建时间',
  `update_date` DATETIME NULL COMMENT '更新时间',
  `del_flag` CHAR(1) NULL DEFAULT 0 COMMENT '删除标记\n1：删除\n0：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='系统用户';

INSERT INTO `sys_user` VALUES ('1', 'admin', '123', '超级管理员', 'administrator', '1', '超级管理员', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('2', 'admin2', '123', '普通用户2', 'administrator', '1', '普通用户2', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('3', 'admin3', '123', '普通用户3', 'administrator', '1', '普通用户3', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('4', 'admin4', '123', '普通用户4', 'administrator', '1', '普通用户4', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('5', 'admin5', '123', '普通用户5', 'administrator', '1', '普通用户5', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('6', 'admin6', '123', '普通用户6', 'administrator', '1', '普通用户6', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('7', 'admin7', '123', '普通用户7', 'administrator', '1', '普通用户7', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('8', 'admin8', '123', '普通用户8', 'administrator', '0', '普通用户8', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('9', 'admin9', '123', '普通用户9', 'administrator', '0', '普通用户9', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('10', 'admin10', '123', '普通用户10', 'administrator', '0', '普通用户10', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('11', 'admin11', '123', '普通用户11', 'administrator', '0', '普通用户11', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_user` VALUES ('12', 'admin12', '123', '普通用户12', 'administrator', '0', '普通用户12', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` VARCHAR(255) NOT NULL COMMENT '编号',
  `roleName` VARCHAR(255) NOT NULL COMMENT '角色名称',
  `enabled` CHAR(1) NOT NULL DEFAULT '1' COMMENT '是否可用\n1：可用\n0：停用',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='系统角色';

INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '1', '管理员角色');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER', '1', '用户角色');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` VARCHAR(36) NOT NULL COMMENT '编号',
  `parent_id` VARCHAR(64) NOT NULL COMMENT '父ID',
  `parent_ids` VARCHAR(2000) NOT NULL COMMENT '树ID',
  `menuName` VARCHAR(100) NOT NULL COMMENT '菜单名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `href` VARCHAR(2000) DEFAULT NULL COMMENT '链接',
  `icon` VARCHAR(100) DEFAULT NULL COMMENT '图标',
  `is_show` CHAR(1) DEFAULT '1' COMMENT '是否显示\n1：显示\n0：隐藏',
  `permission` VARCHAR(200) DEFAULT NULL COMMENT '权限标识',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_date` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
  `del_flag` CHAR(1) DEFAULT '0' COMMENT '删除标记\n1：删除\n0：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

INSERT INTO `sys_menu` VALUES ('07fb63cf51d74e31b095d42b3dfb7686', '1caf32bb9f6f42e99b1d9413ebe84171', ',50223b0d0c014e848296f7580df517e71caf32bb9f6f42e99b1d9413ebe84171', '修改', '20', null, null, '0', 'trip:user:edit', null, '2016-10-20 11:56:31', '2016-10-20 11:56:31', '0');
INSERT INTO `sys_menu` VALUES ('1caf32bb9f6f42e99b1d9413ebe84171', '50223b0d0c014e848296f7580df517e7', ',50223b0d0c014e848296f7580df517e7,', '用户信息', '10', 'app.trip.user.list', 'glyphicon glyphicon-qrcode', '1', '', '', '2016-10-17 10:40:33', '2016-10-20 11:55:45', '0');
INSERT INTO `sys_menu` VALUES ('50223b0d0c014e848296f7580df517e7', '', ',', '用户管理', '20', null, 'glyphicon glyphicon-user', '1', null, null, '2016-10-17 10:38:49', '2016-10-17 10:38:49', '0');
INSERT INTO `sys_menu` VALUES ('71', '', ',', '首页', '1', 'app.dashboard', 'glyphicon glyphicon-dashboard', '1', 'trip:dashboard:view', '', '2015-10-20 08:00:00', '2016-12-26 11:13:42', '0');
INSERT INTO `sys_menu` VALUES ('863b9d86e81f4e3599ce6e594c2e4932', '1caf32bb9f6f42e99b1d9413ebe84171', ',50223b0d0c014e848296f7580df517e7,1caf32bb9f6f42e99b1d9413ebe84171,', '查看', '10', null, null, '0', 'trip:user:view', null, '2016-10-20 11:56:08', '2016-10-20 11:56:08', '0');
INSERT INTO `sys_menu` VALUES ('92', '', '', '系统设置', '60', '', 'glyphicon glyphicon-book', '1', '', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('921', '92', '92,', '菜单管理', '30', 'app.sys.menu.list', 'glyphicon glyphicon-picture', '1', '', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('9211', '921', '92,921,', '查看', '30', '', '', '0', 'sys:menu:view', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('9212', '921', '92,921,', '修改', '40', '', '', '0', 'sys:menu:edit', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('923', '92', '92,', '用户管理', '10', 'app.sys.user.list', 'glyphicon glyphicon-tag', '1', '', '', '2015-10-20 08:00:00', '2016-10-09 16:11:37', '0');
INSERT INTO `sys_menu` VALUES ('9231', '923', '92,923,', '查看', '30', '', '', '0', 'sys:user:view', '', '2015-10-20 08:00:00', '2016-10-12 16:28:45', '0');
INSERT INTO `sys_menu` VALUES ('9232', '923', '92,923,', '修改', '40', '', '', '0', 'sys:user:edit', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('924', '92', '92,', '角色管理', '20', 'app.sys.role.list', 'glyphicon  glyphicon-list-alt', '1', '', '', '2015-10-20 08:00:00', '2016-10-09 16:11:44', '0');
INSERT INTO `sys_menu` VALUES ('9241', '924', '92,924,', '查看', '30', '', '', '0', 'sys:role:view', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');
INSERT INTO `sys_menu` VALUES ('9242', '924', '92,924,', '修改', '40', '', '', '0', 'sys:role:edit', '', '2015-10-20 08:00:00', '2015-10-20 08:00:00', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(255) NOT NULL DEFAULT '',
  `menu_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_role_menu` VALUES ('1', '07fb63cf51d74e31b095d42b3dfb7686');
INSERT INTO `sys_role_menu` VALUES ('1', '1caf32bb9f6f42e99b1d9413ebe84171');
INSERT INTO `sys_role_menu` VALUES ('1', '50223b0d0c014e848296f7580df517e7');
INSERT INTO `sys_role_menu` VALUES ('1', '71');
INSERT INTO `sys_role_menu` VALUES ('1', '863b9d86e81f4e3599ce6e594c2e4932');
INSERT INTO `sys_role_menu` VALUES ('1', '92');
INSERT INTO `sys_role_menu` VALUES ('1', '921');
INSERT INTO `sys_role_menu` VALUES ('1', '9211');
INSERT INTO `sys_role_menu` VALUES ('1', '9212');
INSERT INTO `sys_role_menu` VALUES ('1', '923');
INSERT INTO `sys_role_menu` VALUES ('1', '9231');
INSERT INTO `sys_role_menu` VALUES ('1', '9232');
INSERT INTO `sys_role_menu` VALUES ('1', '924');
INSERT INTO `sys_role_menu` VALUES ('1', '9241');
INSERT INTO `sys_role_menu` VALUES ('1', '9242');
INSERT INTO `sys_role_menu` VALUES ('2', '71');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(255) NOT NULL DEFAULT '',
  `role_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');