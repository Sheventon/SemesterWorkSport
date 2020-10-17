--liquibase formatted sql

--changeset DinarShagaliev:2
create table user(
    id bigint unsigned primary key auto_increment ,
    name varchar(32),
    surname varchar(32),
    patronymic varchar(32),
    email varchar(60),
    password char(88)
);

--rollback drop table user