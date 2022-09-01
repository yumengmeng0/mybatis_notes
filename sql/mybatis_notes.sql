DROP DATABASE IF EXISTS mybatis_db ;

CREATE DATABASE `mybatis_db`;
USE `mybatis_db`;
CREATE TABLE `user` (
  `id` INT (11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR (32) NOT NULL COMMENT '用户名称',
  `birthday` DATETIME DEFAULT NULL COMMENT '生日',
  `sex` CHAR(1) DEFAULT NULL COMMENT '性别',
  `address` VARCHAR (256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- insert....
 INSERT INTO `user` (
  `id`,
  `username`,
  `birthday`,
  `sex`,
  `address`
)
VALUES
  (
    1,
    '子慕',
    '2020-11-11 00:00:00',
    '男',
    '北京海淀'
  ),
  (
    2,
    '应颠',
    '2020-12-12 00:00:00',
    '男',
    '北京海淀'
  );


SELECT DATABASE();


# 查询所有订单，并查询出每个订单所属用户
SELECT * FROM orders o LEFT JOIN USER u ON o.`uid` = u.`id`;


# 查询所有用户，并查询出每个用户的所有订单
SELECT u.*,o.id oid, o.ordertime, o.uid, o.total FROM orders o RIGHT JOIN USER u ON o.uid = u.id;


# 多对多查询，查询所有用户同时查出该用户的所有角色
SELECT u.*,r.id rid,r.rolename,r.roleDesc FROM USER u LEFT JOIN sys_user_role ur ON ur.userid = u.id
	LEFT JOIN sys_role r ON ur.roleid = r.id


SELECT * FROM sys_role r LEFT JOIN sys_user_role ur ON r.id = ur.roleid


 SELECT * FROM orders WHERE id = 1
