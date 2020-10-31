-- liquibase formatted sql

-- changeset DinarShagaliev:5

create table trainer(
    id bigint unsigned primary key auto_increment,
    user_id bigint unsigned not null,
    phone_number varchar(12),
    foreign key (user_id) references user(id)
);

-- rollback drop table trainer