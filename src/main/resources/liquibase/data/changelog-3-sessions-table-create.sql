-- liquibase formatted sql

-- changeset DinarShagaliev:3

create table cookie (
    user_id bigint unsigned not null,
    session_id char(36) not null,
    foreign key(user_id) references user(id)
);

-- rollback drop cookie