

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
