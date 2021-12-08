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

create table st_article
(
    id             int auto_increment
        primary key,
    alias          varchar(100)  null comment '网址别名',
    title          varchar(100)  null comment '标题',
    content        longtext      null comment '正文',
    author         varchar(100)  null comment '作者',
    keywords       varchar(255)  null comment '关键词，英文逗号分隔',
    summary        varchar(1000) null comment '摘要',
    category       varchar(100)  null comment '分类',
    category_id    int           null comment '分类id',
    view_count     int default 0 null comment '观看次数',
    thumb_count    int default 0 null comment '点赞次数',
    publish_status char(10)      null comment '文章状态，1-发布，0-草稿',
    publish_time   datetime      null comment '发表时间',
    create_time    datetime      null comment '创建时间',
    constraint uid_st_article_alias
        unique (alias)
)
    comment '文章表' charset = utf8;

create index st_article_idx_category
    on st_article (category);

create index st_article_idx_category_publish_status
    on st_article (category, publish_status);

create index st_article_idx_category_publish_status_publish_time
    on st_article (category, publish_status, publish_time);

create table st_category
(
    id             int auto_increment
        primary key,
    category       varchar(100) charset utf8 null comment '分类',
    category_alias varchar(20)               null comment '别名',
    weight         int                       null comment '权重，越小越靠前',
    intro          varchar(1000)             null comment '分类描述',
    create_time    datetime                  null comment '创建时间',
    constraint uid_st_category_category
        unique (category),
    constraint uid_st_category_category_alias
        unique (category_alias)
)
    comment '系统分类表' charset = utf8mb4;

create index st_category_idx_weight
    on st_category (weight);

create table st_config
(
    id           int auto_increment
        primary key,
    config_key   varchar(20)   null comment '配置Key',
    config_val   varchar(255)  null comment '配置Val',
    config_group varchar(20)   null comment '配置分组',
    intro        varchar(1000) null comment '配置描述',
    status       char(2)       null comment '状态，1-启用，0-禁用',
    create_time  datetime      null comment '创建时间'
)
    comment '系统配置表' charset = utf8mb4;

create index st_config_idx_config_group
    on st_config (config_group);

create index st_config_idx_config_key
    on st_config (config_key);

create table st_data
(
    id           int auto_increment
        primary key,
    data_group   varchar(20) null comment '数据分组',
    data_key     varchar(50) null comment '数据Key',
    data_content longtext    null comment '数据内容',
    create_time  datetime    null comment '创建时间'
)
    comment '文章表' charset = utf8;

create index st_data_idx_data_group
    on st_data (data_group);

create index st_data_idx_data_key
    on st_data (data_key);

create index st_data_idx_group_key
    on st_data (data_group, data_key);

create table st_menu
(
    id          int auto_increment
        primary key,
    menu_name   varchar(20) charset utf8 null comment '菜单名',
    menu_link   varchar(100)             null comment '链接',
    menu_group  varchar(20)              null comment '菜单所属组',
    weight      int                      null comment '权重，越小越靠前',
    intro       varchar(1000)            null comment '菜单介绍',
    create_time datetime                 null comment '创建时间'
)
    comment '系统菜单表' charset = utf8mb4;

create index st_menu_idx_menu_group_weight
    on st_menu (menu_group, weight);

create table st_user
(
    id          int auto_increment comment '用户id'
        primary key,
    username    varchar(50)      null comment '账号名称',
    show_name   varchar(255)     null comment '显示名称',
    password    varchar(255)     null comment '密码',
    status      char default '1' null comment '是否启用：1启用 0停用',
    mobile      varchar(30)      null comment '手机',
    email       varchar(255)     null comment '邮箱',
    create_time datetime         null comment '创建时间',
    constraint uid_st_user_username
        unique (username)
)
    comment '用户表' engine = MyISAM
                  charset = utf8mb4;

create table ste_poem
(
    id           int auto_increment
        primary key,
    poem_name    varchar(100) null comment '诗的名字',
    poem_author  varchar(100) null comment '诗的作者',
    poem_content longtext     null comment '诗的内容',
    create_time  datetime     null comment '创建时间'
)
    comment '诗歌表' charset = utf8;

create table ste_poem_atom
(
    id           int auto_increment
        primary key,
    poem_id      int           null comment '诗歌id',
    poem_name    varchar(100)  null comment '诗的名字',
    atom_content varchar(1000) null comment '原子段落内容',
    atom_order   int           null comment '原子排序',
    create_time  datetime      null comment '创建时间'
)
    comment '诗歌原子分段表' charset = utf8;

create table ste_poem_name
(
    id            int auto_increment
        primary key,
    poem_id       int           null comment '诗歌id',
    atom_id       int           null comment '原子id',
    name_keywords varchar(100)  null comment '关键词',
    people_name   varchar(100)  null comment '名字',
    poem_name     varchar(100)  null comment '诗的名字',
    atom_content  varchar(1000) null comment '原子段落内容',
    create_time   datetime      null comment '创建时间'
)
    comment '诗歌名字表' charset = utf8;

INSERT INTO st_user (username, show_name, password, status, mobile, email, create_time) VALUES ('singte', 'singte', '$2a$10$Bn/rAOYbo4rnzRroc1gu3..GhC/QpJq4zkQebilC6JXEJgRvW13eS', '1', null, null, now());
