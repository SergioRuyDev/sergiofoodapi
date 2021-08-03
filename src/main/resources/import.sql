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

insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, address_city_id, address_postalcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '10292', '5TH Avenue', '1000', 'Yorkville');
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (3, 'Tuk Tuk Indian Food', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (4, 'Carnival Brazilian', 25, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (5, 'Brazilian Beef', 20, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (6, 'Sumo Japan', 30, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (7, 'Sashimi Oss', 19, 4, utc_timestamp, utc_timestamp);

insert into payment_method (id, description) values (1, 'Credit Card');
insert into payment_method (id, description) values (2, 'Debit Card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Permit consult kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Permit edit kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);