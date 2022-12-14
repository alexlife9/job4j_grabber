--до нормализации
create table movie_rental
(
    name    text,
    address text,
    films   text,
    gender  text
);

--вставляем данные
insert into movie_rental(name, address, films, gender)
values ('ольга егорова', 'казанский, 14', 'пираты, матрица-3', 'женский'),
       ('иванов сергей', 'центральная, 40, кв. 95', 'капекс, интерастеллар', 'мужской'),
       ('иванов сергей', 'ленина, 59, кв. 37', 'матрица-3', 'мужской');

--дропаем таблицу и создаем 1НФ
--Основные критерии:
--Все строки должны быть различными.
--Все элементы внутри ячеек должны быть атомарными (не списками).
drop table movie_rental;

--добавили в таблицу первичный ключ
create table movie_rental
(
    id      serial primary key,
    name    text,
    address text,
    films   text,
    gender  text
);

--выделяем отдельные фильмы из ячеек. получили 1НФ
insert into movie_rental(name, address, films, gender)
values ('ольга егорова', 'казанский, 14', 'пираты', 'женский'),
       ('ольга егорова', 'казанский, 14', 'матрица-3', 'женский'),
       ('иванов сергей', 'центральная, 40, кв. 95', 'капекс', 'мужской'),
       ('иванов сергей', 'центральная, 40, кв. 95', 'интерастеллар', 'мужской'),
       ('иванов сергей', 'ленина, 59, кв. 37', 'матрица-3', 'мужской');

--дропаем таблицу и создаем 2НФ
drop table movie_rental;

--чтобы получить 2НФ, делаем две таблицы: юзеры и фильмы
--Основные критерии 2НФ:
--Таблица должна находиться в первой нормальной форме.
--Любое её поле, не входящее в состав первичного ключа, функционально полно зависит от первичного ключа.
create table users
(
    id      serial primary key,
    name    text,
    address text,
    gender  text,
    UNIQUE (name, address) --делаем связку, потому что два человека с одинаковыми ФИО могут жить по разным адресам
);

create table movie_rental
(
    id       serial primary key,
    films    text,
    users_id int references users (id)
);

--добавляем записи
insert into users(name, address, gender)
values ('ольга егорова', 'казанский, 14', 'женский'),
       ('иванов сергей', 'центральная, 40, кв. 95', 'мужской'),
       ('иванов сергей', 'ленина, 59, кв. 37', 'мужской');

insert into movie_rental(films, users_id)
values ('пираты', 1),
       ('матрица-3', 1),
       ('капекс', 2),
       ('интерастеллар', 2),
       ('матрица-3', 3);


--дропаем таблицы и создаем 3НФ
drop table movie_rental;
drop table users;


--чтобы получить 3НФ, избавляемся от транзитивной зависимости в виде поля гендер (чтобы это ни значило)
-- и делаем три таблицы: юзеры, пол, фильмы.
--Основные критерии 3НФ:
--Таблица должна находиться в первой нормальной форме.
--Любое её поле, не входящее в состав первичного ключа, функционально полно зависит от первичного ключа.

create table user_gender
(
    id     serial primary key,
    gender text
);

create table users
(
    id             serial primary key,
    name           text,
    address        text,
    user_gender_id int references user_gender (id),
    UNIQUE (name, address)
);

create table movie_rental
(
    id       serial primary key,
    films    text,
    users_id int references users (id)
);

--добавляем записи
insert into user_gender(gender)
values ('женский'),
       ('мужской')
;

insert into users(name, address, user_gender_id)
values ('ольга егорова', 'казанский, 14', 1),
       ('иванов сергей', 'центральная, 40, кв. 95', 2),
       ('иванов сергей', 'ленина, 59, кв. 37', 2);


insert into movie_rental(films, users_id)
values ('пираты', 1),
       ('матрица-3', 1),
       ('капекс', 2),
       ('интерастеллар', 2),
       ('матрица-3', 3);

--смотрим что получилось
select *
from user_gender
join users u on user_gender.id = u.user_gender_id
join movie_rental mr on u.id = mr.users_id
;