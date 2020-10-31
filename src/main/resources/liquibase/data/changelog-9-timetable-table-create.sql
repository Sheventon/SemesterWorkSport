-- liquibase formatted sql

-- changeset DinarShagaliev:9

create table timetable (
    id smallint primary key auto_increment,
    section_id bigint unsigned not null,
    trainer_id bigint unsigned not null,
    day varchar(30) not null,
    start_time TIME not null,
    end_time TIME not null,
    foreign key(section_id) references section(id),
    foreign key(trainer_id) references trainer(id)
);