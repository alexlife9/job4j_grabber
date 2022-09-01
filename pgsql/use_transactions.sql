create table products (
                          id serial primary key,
                          name varchar(50),
                          producer varchar(50),
                          count integer default 0,
                          price integer
);



insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 10, 100);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 20, 200);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 30, 300);

begin transaction isolation level serializable;
select sum(price) from products;
update products set price = 1000 where name = 'product_3';
update products set price = 5000 where name = 'product_4';