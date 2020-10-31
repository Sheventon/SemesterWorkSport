-- liquibase formatted sql

-- changeset DinarShagaliev:8

insert into sport(name)
values ('Волейбол'),
       ('Мини-футбол'),
       ('Плавание'),
       ('Бокс'),
       ('Дзюдо'),
       ('Баскетбол');

insert into user(name, surname, patronymic, email, password)
value ('Антон', 'Шеверда', 'Игоревич', 'tores.fernando.real@gmail.com', 'password');

insert into trainer(user_id, phone_number) values (1, '+79274781377');

insert into section(trainer_id, sport_id)
value (1, 6);

