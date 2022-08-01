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