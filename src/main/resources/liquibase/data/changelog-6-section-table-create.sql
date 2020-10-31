-- liquibase formatted sql

-- changeset DinarShagaliev:6

create table section(
    id bigint unsigned primary key auto_increment,
    trainer_id bigint unsigned not null,
    sport_id tinyint unsigned not null,
    foreign key(trainer_id) references trainer(id),
    foreign key(sport_id) references sport(id)
);