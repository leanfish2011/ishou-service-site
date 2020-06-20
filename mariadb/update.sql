use ishou_site;

-- 2020-06-20，首页网站增加字段存储图标路径
ALTER TABLE `ishou_site`.`site_home`
ADD COLUMN `icon_url` VARCHAR(100) NULL COMMENT '网站图标地址' AFTER `visit_count`;

-- 2020-06-20，个人网站增加字段存储图标路径
ALTER TABLE `ishou_site`.`site_personal`
ADD COLUMN `icon_url` VARCHAR(100) NULL COMMENT '网站图标地址' AFTER `sort_num`;
