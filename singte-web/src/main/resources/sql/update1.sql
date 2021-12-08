
create table seo_data
(
    id          int auto_increment
        primary key,
    date        varchar(20) null comment '日期',
    uid         varchar(20) null comment 'uid',
    user        varchar(50) null comment '用户名',
    category    varchar(50) null comment '分类',
    ip          int         null comment 'ip',
    pv          int         null comment 'pv',
    create_time datetime    null comment '创建时间'
)
    comment 'SEO 数据' charset = utf8;

create index seo_data_idx_all
    on seo_data (date, uid, category);

create index seo_data_idx_date
    on seo_data (date);

create table seo_item
(
    id          int auto_increment
        primary key,
    uid         varchar(20)  null comment 'uid',
    data_type   varchar(10)  null comment '数据类型，cnzz',
    data_url    varchar(255) null comment '获取数据的链接',
    user        varchar(50)  null comment '用户名',
    category    varchar(50)  null comment '分类',
    weight      int          null comment '排序权重',
    create_time datetime     null comment '创建时间'
)
    comment 'SEO-条目' charset = utf8;

create index seo_item_idx_category
    on seo_item (weight, category);

create index seo_item_idx_weight
    on seo_item (weight);
