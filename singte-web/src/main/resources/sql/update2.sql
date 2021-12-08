
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

INSERT INTO st_user (username, show_name, password, status, mobile, email, create_time) VALUES ('singte', 'singte', '$2a$10$Bn/rAOYbo4rnzRroc1gu3..GhC/QpJq4zkQebilC6JXEJgRvW13eS', '1', null, null, now());
