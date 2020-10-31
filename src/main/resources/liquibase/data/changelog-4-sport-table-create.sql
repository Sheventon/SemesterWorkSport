-- liquibase formatted sql

-- changeset DinarShagaliev:4

create table sport (
    id tinyint unsigned primary key auto_increment unique,
    name varchar(50)
);

-- rollback drop table sport