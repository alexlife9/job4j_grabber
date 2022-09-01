create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--начинаем транзакцию
begin transaction;

--вставляем еще один продукт
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

--фиксируем изменения в транзакции:
commit;

--глянем что получилось
select * from products;

--начинаем новую транзакцию
begin transaction;

--удаляем все содержимое таблицы
delete from products;

--удаляем саму таблицу
drop table products;

--откатываем изменения произошедшие с таблицей после начала транзакции
rollback transaction;

--убеждаемся что все данные на месте и таблица не удалена, значит изменения откатились успешно
select * from products;

--начинаем новую транзакцию
begin transaction;

--вставляем еще один продукт
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 17, 45);

--устанавливаем первую точку сохранения
savepoint savepoint_1;

--производим манипуляции с данными
delete from products where price = 115;
update products set price = 75 where name = 'product_1';

--смотрим что получилось
select * from products;

--откатываемся к первой точке сохранения
rollback to savepoint_1;

--и убеждаемся что манипуляции с данными не сохранились
select * from products;

--устанавливаем вторую точку сохранения
savepoint savepoint_2;

--производим манипуляции с данными
delete from products where count < 10;
update products set price = 500 where name = 'product_5';

--проверяем результат
select * from products;

--откатываемся ко второй точке сохранения
rollback to savepoint_2;

--уничтожаем первую точку, автоматически уничтожаются все точки после первой
release savepoint savepoint_1;

--производим манипуляции с данными
delete from products where count = 11;

--пробуем откатиться ко второй точке сохранения
rollback to savepoint_2; --получаем ошибку, потому что она была уничтожена

--завершаем транзакцию
commit;