use ishou_site;

-- 2020-06-20，首页网站增加字段存储图标路径
ALTER TABLE `ishou_site`.`site_home`
ADD COLUMN `icon_url` VARCHAR(100) NULL COMMENT '网站图标地址' AFTER `visit_count`;

-- 2020-06-20，个人网站增加字段存储图标路径
ALTER TABLE `ishou_site`.`site_personal`
ADD COLUMN `icon_url` VARCHAR(100) NULL COMMENT '网站图标地址' AFTER `sort_num`;

-- 2020-06-20，首页网站url字段加长
ALTER TABLE `ishou_site`.`site_home`
CHANGE COLUMN `url` `url` VARCHAR(200) NULL DEFAULT NULL COMMENT '网站地址' ;

-- 2020-06-20，个人网站url字段加长
ALTER TABLE `ishou_site`.`site_personal`
CHANGE COLUMN `url` `url` VARCHAR(200) NULL DEFAULT NULL COMMENT '网站地址' ;

-- 2021-01-16，标签、备注增加长度
ALTER TABLE `ishou_site`.`site_personal`
CHANGE COLUMN `tag` `tag` VARCHAR(500) NULL DEFAULT NULL COMMENT '标签' ;

ALTER TABLE `ishou_site`.`site_personal`
CHANGE COLUMN `remark` `remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '简介' ;

ALTER TABLE `ishou_site`.`site_home`
CHANGE COLUMN `tag` `tag` VARCHAR(500) NULL DEFAULT NULL COMMENT '标签' ;

ALTER TABLE `ishou_site`.`site_home`
CHANGE COLUMN `remark` `remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '简介' ;
