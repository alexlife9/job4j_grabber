-- SQL Select by company

CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

-- Задание:
-- 1. В одном запросе получить:
--  * имена всех person, которые не состоят в компании с id = 5
--  * название компании для каждого человека
--
-- 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
-- Нужно учесть, что таких компаний может быть несколько, и вывести надо информацию о каждой компании.

--Решение

--наполним таблицы данными
insert into company (id, name)
VALUES (1, 'company_1'),
       (2, 'company_2'),
       (3, 'company_3'),
       (4, 'company_4'),
       (5, 'company_5'),
       (6, 'company_6')
;

insert into person(id, name, company_id)
VALUES (1, 'person_1', 1), --одна персона состоит в двух компаниях
       (2, 'person_1', 2),
       (3, 'person_2', 2),
       (4, 'person_3', 3),
       (5, 'person_4', 4), --две персоны состоят в одной компании
       (6, 'person_5', 4),
       (7, 'person_6', 6), --персоны 7 у нас не будет
       (8, 'person_8', 5),
       (9, 'person_9', 5),
       (10, 'person_10', 5)
;

--проверяем корректность добавления данных
select *
from company
         join person p on company.id = p.company_id;

--запрос для всех person, которые не состоят в компании с id = 5 И название компании для каждого такого person
select c.name AS компания, p.name AS персоны
from company AS c
         join person p on c.id = p.company_id
where company_id != 5;

--запрос на название компании с максимальным количеством человек + количество человек в этой компании.
select company.name AS компания, count(person.name) AS количество_персон
from company
         join person on company.id = person.company_id
group by company.name
having count(person.name) = ( --считаем кол-во компаний и открываем подзапрос для выполнения условий
    select max(количество)                           --выбираем и передаем в МАХ аргумент
    from (                                           --из второго подзапроса
             select count(person.name) AS количество --где выбираем кол-во персон
             from company                            --из таблицы company с присоединенной таблицей person
                      join person on company.id = person.company_id
             group by company.name --having всегда завершается group by
         ) as cpк --подзапрос во фром должен иметь псевдоним
);