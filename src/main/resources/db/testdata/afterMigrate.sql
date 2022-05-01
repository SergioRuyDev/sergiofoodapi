set foreign_key_checks = 0;

delete from city;
delete from `group`;
delete from group_permission;
delete from kitchen;
delete from payment_method;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from state;
delete from `user`;
delete from user_group;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table `group` auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table payment_method auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table state auto_increment = 1;
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

insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active, address_city_id, address_postalcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, 1, '10292', '5TH Avenue', '1000', 'Yorkville');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (3, 'Tuk Tuk Indian Food', 15, 2, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (4, 'Carnival Brazilian', 25, 3, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (5, 'Brazilian Beef', 20, 3, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (6, 'Sumo Japan', 30, 4, utc_timestamp, utc_timestamp, true);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, active) values (7, 'Sashimi Oss', 19, 4, utc_timestamp, utc_timestamp, true);

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

insert into group (name) values ('Manager'), ('Sales'), ('Secretary'), ('Register');