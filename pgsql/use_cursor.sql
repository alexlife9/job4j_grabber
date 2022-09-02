create table products (
                          id serial primary key,
                          name varchar(50),
                          count integer default 0,
                          price integer
);

insert into products (name, count, price) VALUES ('product_1', 1, 5);
insert into products (name, count, price) VALUES ('product_2', 2, 10);
insert into products (name, count, price) VALUES ('product_3', 3, 15);
insert into products (name, count, price) VALUES ('product_4', 4, 20);
insert into products (name, count, price) VALUES ('product_5', 5, 25);
insert into products (name, count, price) VALUES ('product_6', 6, 30);
insert into products (name, count, price) VALUES ('product_7', 7, 35);
insert into products (name, count, price) VALUES ('product_8', 8, 40);
insert into products (name, count, price) VALUES ('product_9', 9, 45);
insert into products (name, count, price) VALUES ('product_10', 10, 50);
insert into products (name, count, price) VALUES ('product_11', 11, 55);
insert into products (name, count, price) VALUES ('product_12', 12, 60);
insert into products (name, count, price) VALUES ('product_13', 13, 65);
insert into products (name, count, price) VALUES ('product_14', 14, 70);
insert into products (name, count, price) VALUES ('product_15', 15, 75);
insert into products (name, count, price) VALUES ('product_16', 16, 80);
insert into products (name, count, price) VALUES ('product_17', 17, 85);
insert into products (name, count, price) VALUES ('product_18', 18, 90);
insert into products (name, count, price) VALUES ('product_19', 19, 95);
insert into products (name, count, price) VALUES ('product_20', 20, 100);

BEGIN; --запускаем транзакцию
DECLARE --и только после начала транзакции объявляем курсор
    cursor_products cursor for
    select * from products;

MOVE FORWARD 21 FROM cursor_products; --переместили курсор на 21 строку вперед (вниз списка)

FETCH BACKWARD 3 FROM cursor_products; --прочитали 3 продукта в обратном порядке - вывели 20, 19, 18

FETCH NEXT FROM cursor_products; --прочитали следующий продукт. будет 19, потому что читаем по-умолчанию вперед
                                 --запись аналогична FETCH FROM cursor_products БЕЗ NEXT

FETCH PRIOR FROM cursor_products; --прочитали предыдущий продукт. будет 18

FETCH BACKWARD 18 FROM cursor_products; --прочитали оставшиеся 17 продуктов. от 17 до 1.

CLOSE cursor_products; --закрываем курсор

COMMIT; --обязательно закрываем транзакцию