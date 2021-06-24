insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Brazilian');
insert into kitchen (id, name) values (4, 'Japanese');

insert into restaurant (name, tax_delivery, kitchen_id) values ('Thai Gourmet', 10, 1);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Thai Delivery', 9.50, 1);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Tuk Tuk Indian Food', 15, 2);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Carnival Brazilian', 25, 3);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Brazilian Beef', 20, 3);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Sumo Japan', 30, 4);
insert into restaurant (name, tax_delivery, kitchen_id) values ('Sashimi Oss', 19, 4);

insert into state (id, name) values (1, 'New York');
insert into state (id, name) values (2, 'Florida');
insert into state (id, name) values (3, 'California');

insert into city (id, name, state_id) values (1, 'New York City', 1);
insert into city (id, name, state_id) values (2, 'Western New York', 1);
insert into city (id, name, state_id) values (3, 'Miami', 2);
insert into city (id, name, state_id) values (4, 'Tampa', 2);
insert into city (id, name, state_id) values (5, 'Los Angeles', 3);

insert into payment_method (id, description) values (1, 'Credit Card');
insert into payment_method (id, description) values (2, 'Debit Card');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Permit consult kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Permit edit kitchens');