
ALTER TABLE `seo_item`
    ADD COLUMN `data_type` varchar(10) NULL DEFAULT NULL COMMENT '数据类型，cnzz' AFTER `uid`;

ALTER TABLE `seo_item`
    ADD COLUMN `data_url` varchar(255) NULL DEFAULT NULL COMMENT '获取数据的链接' AFTER `data_type`;
