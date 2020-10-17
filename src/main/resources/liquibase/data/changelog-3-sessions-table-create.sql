--liquibase formatted sql

--changeset DinarShagaliev:3

create table cookie (
    user_id bigint unsigned,
    session_id char(36),
    foreign key(user_id) references user(id)
);

--rollback drop user_session_cookie