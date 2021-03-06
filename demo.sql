DROP DATABASE IF EXISTS `kaoqinsystem`;
CREATE DATABASE `kaoqinsystem`;
use `kaoqinsystem`;

set global innodb_large_prefix=on;

set global innodb_file_per_table=on;

set global innodb_file_format=BARRACUDA;

DROP DATABASE IF EXISTS `nx_system_file_info`;
CREATE TABLE `nx_system_file_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`originName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始文件名',
`fileName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存储文件名',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='文件信息表';

DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '姓名',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`nickName` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '昵称',
`sex` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '性别',
`age` int(10) COMMENT '年龄',
`birthday` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '生日',
`phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '手机号',
`address` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '地址',
`code` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '编号',
`email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '邮箱',
`cardId` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '身份证',
`level` int(10) NOT NULL DEFAULT '1' COMMENT '权限等级',
UNIQUE KEY `uk_name` (`name`) USING BTREE,
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员信息表';


DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '姓名',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`nickName` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '昵称',
`sex` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '性别',
`age` int(10) COMMENT '年龄',
`birthday` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '生日',
`phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '手机号',
`address` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '地址',
`email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '邮箱',
`cardId` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '身份证',
`level` int(10) NOT NULL DEFAULT '2' COMMENT '权限等级',
UNIQUE KEY `uk_name` (`name`) USING BTREE,
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='员工信息表';


DROP TABLE IF EXISTS `canshu_info`;
CREATE TABLE `canshu_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`shangban` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '上班时间',
`xiaban` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '下班时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='考勤参数信息表';


DROP TABLE IF EXISTS `kaoqin_info`;
CREATE TABLE `kaoqin_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`shangban` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '上班时间',
`xiaban` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '下班时间',
`shangbanStatus` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '上班考勤',
`xiabanStatus` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '下班考勤',
`userId` bigint(20) NULL COMMENT '员工id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='考勤信息表';


DROP TABLE IF EXISTS `advertiser_info`;
CREATE TABLE `advertiser_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '公告名称',
`content` longtext COMMENT '公告内容',
`time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '公告时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='公告信息表';


DROP TABLE IF EXISTS `qingjia_info`;
CREATE TABLE `qingjia_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '请假人',
`liyou` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '请假理由',
`tianshu` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '请假天数',
`time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '请假时间',
`userName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提交人',
`level` int(10) DEFAULT NULL COMMENT '用户等级',
`status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核状态',
`reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '理由',
`verifyName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核人',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='请假申请信息表';


DROP TABLE IF EXISTS `xiujia_info`;
CREATE TABLE `xiujia_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '休假人',
`time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '提交时间',
`tianshu` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '休假天数',
`liyou` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '休假理由',
`userName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '提交人',
`level` int(10) DEFAULT NULL COMMENT '用户等级',
`status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核状态',
`reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '理由',
`verifyName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核人',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='休假申请信息表';


DROP TABLE IF EXISTS `message_info`;
CREATE TABLE `message_info` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '留言名称',
`content` text COMMENT '留言内容',
`time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '留言时间',
`parentId` bigint(20) DEFAULT '0' COMMENT '父id',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='留言信息表';


INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 22, '2021-03-20 18:26:30', '18843232356', '上海市', '111', 'aa@163.com', '342425199001116372', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (2, 'admin2', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 23, '2021-03-20 18:26:30', '18843232356', '北京市', '222', 'bb@163.com', '342425199001116376', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (3, 'admin3', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '女', 32, '2021-03-20 18:26:30', '18843232356', '合肥市', '333', 'cc@163.com', '342425199001116323', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (4, 'admin4', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '女', 24, '2021-03-20 18:26:30', '18843232356', '北京市', '444', 'hahaha@126.com', '342425199001116837', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (5, 'admin5', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 25, '2021-03-20 18:26:30', '18843232356', '合肥市', '555', 'hello@126.com', '342425199001116309', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (6, 'admin6', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 26, '2021-03-20 18:26:30', '18843232356', '北京市', '666', '1212@qq.com', '342425199001116984', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (7, 'admin7', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 21, '2021-03-20 18:26:30', '18843232356', '合肥市', '777', '8765@qq.com', '342425199001116303', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (8, 'admin8', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 31, '2021-03-20 18:26:30', '18843232356', '北京市', '888', '9080@qq.com', '342425199001116910', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (9, 'admin9', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '男', 34, '2021-03-20 18:26:30', '18843232356', '合肥市', '999', 'shjs@126.com', '342425199001116345', 1);
INSERT INTO `admin_info` (id, name, password, nickName, sex, age, birthday, phone, address, code, email, cardId, level) VALUES (10, 'admin10', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '女', 33, '2021-03-20 18:26:30', '18843232356', '深圳市', '000', '666@qq.com', '342425199001116234', 1);


INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (1, '张天志', 'e10adc3949ba59abbe56e057f20f883e', '老张', '男', 22, '2021-03-20 18:26:30', '18843232356', '上海市', 'aa@163.com', '342425199001116372', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (2, '赵千里', 'e10adc3949ba59abbe56e057f20f883e', '老赵', '男', 23, '2021-03-20 18:26:30', '18843232356', '北京市', 'bb@163.com', '342425199001116376', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (3, '钱优优', 'e10adc3949ba59abbe56e057f20f883e', '老钱', '女', 32, '2021-03-20 18:26:30', '18843232356', '合肥市', 'cc@163.com', '342425199001116323', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (4, '贾宏鑫', 'e10adc3949ba59abbe56e057f20f883e', '老贾', '女', 24, '2021-03-20 18:26:30', '18843232356', '北京市', 'hahaha@126.com', '342425199001116837', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (5, '夏其林', 'e10adc3949ba59abbe56e057f20f883e', '老夏', '男', 25, '2021-03-20 18:26:30', '18843232356', '合肥市', 'hello@126.com', '342425199001116309', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (6, '倪标生', 'e10adc3949ba59abbe56e057f20f883e', '老倪', '男', 26, '2021-03-20 18:26:30', '18843232356', '北京市', '1212@qq.com', '342425199001116984', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (7, '孙晓哈', 'e10adc3949ba59abbe56e057f20f883e', '老孙', '男', 21, '2021-03-20 18:26:30', '18843232356', '合肥市', '8765@qq.com', '342425199001116303', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (8, '李锐', 'e10adc3949ba59abbe56e057f20f883e', '老李', '男', 31, '2021-03-20 18:26:30', '18843232356', '北京市', '9080@qq.com', '342425199001116910', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (9, '吴腊苏', 'e10adc3949ba59abbe56e057f20f883e', '老吴', '男', 34, '2021-03-20 18:26:30', '18843232356', '合肥市', 'shjs@126.com', '342425199001116345', 2);
INSERT INTO `user_info` (id, name, password, nickName, sex, age, birthday, phone, address, email, cardId, level) VALUES (10, '潘晓章', 'e10adc3949ba59abbe56e057f20f883e', '老潘', '女', 33, '2021-03-20 18:26:30', '18843232356', '深圳市', '666@qq.com', '342425199001116234', 2);


INSERT INTO `advertiser_info` (id, name, content, time) VALUES (1, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (2, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (3, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (4, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (5, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (6, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (7, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (8, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (9, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');
INSERT INTO `advertiser_info` (id, name, content, time) VALUES (10, '系统公告', '这是系统公告，管理员可以在后台修改', '2021-03-20 18:26:30');


