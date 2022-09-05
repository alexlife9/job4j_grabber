create schema aggregator;

create table post
(
    id      serial primary key,
    name    text,                -- название вакансии
    text    text,                -- описание вакансии
    link    varchar(250) UNIQUE, -- ссылка на вакансию
    created date                 -- дата создания
);