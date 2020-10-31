-- liquibase formatted sql

-- changeset DinarShagaliev:2
create table user(
    id bigint unsigned primary key auto_increment ,
    name varchar(32) not null,
    surname varchar(32) not null,
    patronymic varchar(32),
    email varchar(60) not null,
    password char(88) not null
);

-- rollback drop table user