-- liquibase formatted sql

-- changeset DinarShagaliev:7

create table record(
    id bigint unsigned primary key auto_increment,
    user_id bigint unsigned not null,
    section_id bigint unsigned not null,
    foreign key (user_id) references user(id),
    foreign key (section_id) references section(id)

);