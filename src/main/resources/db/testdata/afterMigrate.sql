set foreign_key_checks = 0;

delete from city;
delete from kitchen;
delete from state;
delete from payment_method;
delete from `group`;
delete from group_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from restaurant_user_responsible;
delete from `user`;
delete from user_group;
delete from `order`;
delete from item_ordered;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table `group` auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table `user` auto_increment = 1;

insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Brazilian');
insert into kitchen (id, name) values (4, 'Japanese');

insert into state (id, name) values (1, 'New York');
insert into state (id, name) values (2, 'Florida');
insert into state (id, name) values (3, 'California');

insert into city (id, name, state_id) values (1, 'New York City', 1);
insert into city (id, name, state_id) values (2, 'Western New York', 1);
insert into city (id, name, state_id) values (3, 'Miami', 2);
insert into city (id, name, state_id) values (4, 'Tampa', 2);
insert into city (id, name, state_id) values (5, 'Los Angeles', 3);

insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open, address_city_id, address_postalcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '10292', '5TH Avenue', '1000', 'Yorkville');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open, address_city_id, address_postalcode, address_street, address_number, address_district) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true, 2, '13392', '14TH Avenue', '100', 'Yank-ville');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open, address_city_id, address_postalcode, address_street, address_number, address_district) values (3, 'Tuk Tuk Indian Food', 15, 2, utc_timestamp, utc_timestamp, true, true,  3, '292', 'Long Street', '29', 'Florida');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open, address_city_id, address_postalcode, address_street, address_number, address_district) values (4, 'Carnival Brazilian', 25, 3, utc_timestamp, utc_timestamp, true, true,  4, '12', 'Small Street', '1000', 'Gatsby-Village');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open, address_city_id, address_postalcode, address_street, address_number, address_district) values (5, 'Brazilian Beef', 20, 3, utc_timestamp, utc_timestamp, true, true, 5, '102', '3TH Avenue', '10', 'Josh-Ville');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open) values (6, 'Sumo Japan', 30, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, open) values (7, 'Sashimi Oss', 19, 4, utc_timestamp, utc_timestamp, true, true);

insert into payment_method (id, description) values (1, 'Credit Card');
insert into payment_method (id, description) values (2, 'Debit Card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Permit consult kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Permit edit kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_id) values ('Pork with barbecue sauce', 'Delicious pork meat in especial sauce', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Shrimp thai', '16 big shrimps in the spicy sauce', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Hot salad will grilled beef', 'Green salad with thin slices cuts of select premium Angus meat', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Traditional indian bread with garlic on top', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Chicken cubes prepared with especial curry sauce', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Beef Anchor', 'Big and soft cut, prepared with selected seasoning', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Classic australian cut, served with our especial barbecue sauce', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sandwich X-Tudo', 'Brazilian sandwich with cheese, hamburger, bacon, egg, salad and especial mayo', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id) values ('Barbecue Stick', 'came with cassava flour and vinegar sauce', 8, 1, 6);

insert into `group` (id, name) values (1, 'Manager'), (2, 'Sales'), (3, 'Secretary'), (4, 'Register');

insert into group_permission (group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into `user` (id, name, email, password, date_register) values
(1, 'Sergio Ruy', 'sergio@gmail.com', '123', utc_timestamp),
(2, 'Andressa Tomiyama', 'andressa@hotmail.com', '123', utc_timestamp),
(3, 'Julia Tomiyama', 'julia@gmail.com', '123', utc_timestamp),
(4, 'Joao Luiz', 'joao@gmail.com', '123', utc_timestamp),
(5, 'Jose da Silva', 'jose@gmail.com', '123', utc_timestamp);

insert into user_group (user_id, group_id) values (1, 1), (1, 2), (2, 2);

insert into restaurant_user_responsible (restaurant_id, user_id) values (1, 1), (2, 2), (3, 3);

insert into `order` (id, restaurant_id, user_customer_id, payment_method_id, address_city_id, address_postalcode,
                   address_street, address_number, address_complement, address_district,
                    status, created_date, subtotal, tax_delivery, total_amount)
values (1, 1, 1, 1, 1, '38400-000', 'Street 99th Baker', '500', 'flat 801', 'East Zone',
        'CREATED', utc_timestamp, 298.90, 10, 308.90);

insert into item_ordered (id, order_id, product_id, quantity, unit_price, total_price, observations)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_ordered (id, order_id, product_id, quantity, unit_price, total_price, observations)
values (2, 1, 2, 2, 110, 220, 'Without Spicy, please.');


insert into `order` (id, restaurant_id, user_customer_id, payment_method_id, address_city_id, address_postalcode,
                   address_street, address_number, address_complement, address_district,
                   status, created_date, subtotal, tax_delivery, total_amount)
values (2, 4, 1, 2, 1, '38400-111', '14th Avenue', '300', 'House 2', 'Downtown',
        'CREATED', utc_timestamp, 79, 0, 79);

insert into item_ordered (id, order_id, product_id, quantity, unit_price, total_price, observations)
values (3, 2, 6, 1, 79, 79, 'Well done');

