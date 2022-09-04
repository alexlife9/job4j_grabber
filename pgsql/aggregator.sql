create schema aggregator;

create table post
(
    id      serial primary key,
    name    text,        -- название вакансии
    text    text,        -- описание вакансии
    link    text UNIQUE, -- ссылка на вакансию
    created date         -- дата создания
);