create table payment_method (
        id bigint not null auto_increment,
        description varchar(60) not null,
        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table `group` (
        id bigint not null auto_increment,
        name varchar(60) not null,

        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table group_permission (
        group_id bigint not null,
        permission_id bigint not null,

        primary key (group_id, permission_id)
) engine=InnoDB default charset=utf8mb4;

create table permission (
        id bigint not null auto_increment,
        description varchar(60) not null,
        name varchar(100) not null,

        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table product (
        id bigint not null auto_increment,
        restaurant_id bigint not null,
        name varchar(80) not null,
        description text not null,
        price decimal(10,2) not null,
        active tinyint(1) not null,

        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurant (
        id bigint not null auto_increment,
        kitchen_id bigint not null,
        name varchar(80) not null,
        tax_delivery decimal(12,2) not null,
        date_update datetime not null,
        date_register datetime not null,

        address_city_id bigint,
        address_postalcode varchar(10),
        address_street varchar(100),
        address_number varchar(20),
        address_complement varchar(60),
        address_district varchar(60),

        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurant_payment_method (
        restaurant_id bigint not null,
        payment_method_id bigint not null,

        primary key (restaurant_id, payment_method_id)
) engine=InnoDB default charset=utf8mb4;

create table user (
        id bigint not null auto_increment,
        name varchar(255) not null,
        email varchar(255) not null,
        password varchar(255) not null,
        date_register datetime not null,

        primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table user_group (
        user_id bigint not null,
        group_id bigint not null,

        primary key (user_id, group_id)
) engine=InnoDB default charset=utf8mb4;





alter table group_permission add constraint fk_group_permission_permission
foreign key (permission_id) references permission (id);

alter table group_permission add constraint fk_group_permission_group
foreign key (group_id) references `group` (id);

alter table product add constraint fk_product_restaurant
foreign key (restaurant_id) references restaurant (id);


alter table restaurant add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_payment_method add constraint fk_rest_method_paymt_method_paymt
foreign key (payment_method_id) references payment_method (id);

alter table restaurant_payment_method add constraint FKbjuwyavt07p2uihdqt6jtmkyj
foreign key (restaurant_id) references restaurant (id);

alter table user_group add constraint fk_user_group_group
foreign key (group_id) references `group` (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references user (id);



#     alter table group_permission
#        add constraint FKss14p30qbokhpkpdov4ha3wro
#        foreign key (permission_id)
#        references permission (id)
#
#     alter table group_permission
#        add constraint FK4twq9e99a6jwgl699bqlu5tsc
#        foreign key (group_id)
#        references group (id)
#
#     alter table product
#        add constraint FKp4b7e36gck7975p51raud8juk
#        foreign key (restaurant_id)
#        references restaurant (id)
#
#     alter table restaurant
#        add constraint FK8pcwgn41lfg43d8u82ewn3cn
#        foreign key (address_city_id)
#        references city (id)
#
#     alter table restaurant
#        add constraint FKrur1dqx79jim8s8dvdp16qc3d
#        foreign key (kitchen_id)
#        references kitchen (id)
#
#     alter table restaurant_payment_method
#        add constraint FK5dxd5cjhjqf8eai6xugad3e1g
#        foreign key (payment_method_id)
#        references payment_method (id)
#
#     alter table restaurant_payment_method
#        add constraint FKbjuwyavt07p2uihdqt6jtmkyj
#        foreign key (restaurant_id)
#        references restaurant (id)
#
#     alter table user_group
#        add constraint FKjonf4pqux3h1e687sd18dhcnj
#        foreign key (group_id)
#        references group (id)
#
#     alter table user_group
#        add constraint FK1c1dsw3q36679vaiqwvtv36a6
#        foreign key (user_id)
#        references user (id)
#
# alter table city
#     add constraint FK6p2u50v8fg2y0js6djc6xanit
#         foreign key (state_id)
#             references state (id)

#
# alter table city
#     add constraint FK6p2u50v8fg2y0js6djc6xanit
#         foreign key (state_id)
#             references state (id)

    #     create table city (
#        id bigint not null auto_increment,
#         name varchar(255) not null,
#         state_id bigint not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table group (
#        id bigint not null auto_increment,
#         name varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table group_permission (
#        group_id bigint not null,
#         permission_id bigint not null
#     ) engine=InnoDB
#
#     create table kitchen (
#        id bigint not null auto_increment,
#         name varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table payment_method (
#        id bigint not null auto_increment,
#         description varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table permission (
#        id bigint not null auto_increment,
#         description varchar(255) not null,
#         name varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table product (
#        id bigint not null auto_increment,
#         active bit not null,
#         description varchar(255) not null,
#         name varchar(255) not null,
#         price decimal(19,2) not null,
#         restaurant_id bigint not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table restaurant (
#        id bigint not null auto_increment,
#         address_complement varchar(255),
#         address_district varchar(255),
#         address_number varchar(255),
#         address_postalcode varchar(255),
#         address_street varchar(255),
#         date_register datetime not null,
#         date_update datetime not null,
#         name varchar(255) not null,
#         tax_delivery decimal(19,2) not null,
#         address_city_id bigint,
#         kitchen_id bigint not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table restaurant_payment_method (
#        restaurant_id bigint not null,
#         payment_method_id bigint not null
#     ) engine=InnoDB
#
#     create table state (
#        id bigint not null auto_increment,
#         name varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table user (
#        id bigint not null auto_increment,
#         date_register datetime not null,
#         email varchar(255) not null,
#         name varchar(255) not null,
#         password varchar(255) not null,
#         primary key (id)
#     ) engine=InnoDB
#
#     create table user_group (
#        user_id bigint not null,
#         group_id bigint not null
#     ) engine=InnoDB

# insert into kitchen (id, name) values (1, 'Thai')
# insert into kitchen (id, name) values (2, 'Indian')
# insert into kitchen (id, name) values (3, 'Brazilian')
# insert into kitchen (id, name) values (4, 'Japanese')
# insert into state (id, name) values (1, 'New York')
# insert into state (id, name) values (2, 'Florida')
# insert into state (id, name) values (3, 'California')
# insert into city (id, name, state_id) values (1, 'New York City', 1)
# insert into city (id, name, state_id) values (2, 'Western New York', 1)
# insert into city (id, name, state_id) values (3, 'Miami', 2)
# insert into city (id, name, state_id) values (4, 'Tampa', 2)
# insert into city (id, name, state_id) values (5, 'Los Angeles', 3)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update, address_city_id, address_postalcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '10292', '5TH Avenue', '1000', 'Yorkville')
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (3, 'Tuk Tuk Indian Food', 15, 2, utc_timestamp, utc_timestamp)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (4, 'Carnival Brazilian', 25, 3, utc_timestamp, utc_timestamp)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (5, 'Brazilian Beef', 20, 3, utc_timestamp, utc_timestamp)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (6, 'Sumo Japan', 30, 4, utc_timestamp, utc_timestamp)
# insert into restaurant (id, name, tax_delivery, kitchen_id, date_register, date_update) values (7, 'Sashimi Oss', 19, 4, utc_timestamp, utc_timestamp)
# insert into payment_method (id, description) values (1, 'Credit Card')
# insert into payment_method (id, description) values (2, 'Debit Card')
# insert into payment_method (id, description) values (3, 'Cash')
# insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Permit consult kitchens')
# insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Permit edit kitchens')
# insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3)
# insert into product (name, description, price, active, restaurant_id) values ('Pork with barbecue sauce', 'Delicious pork meat in especial sauce', 78.90, 1, 1)
# insert into product (name, description, price, active, restaurant_id) values ('Shrimp thai', '16 big shrimps in the spicy sauce', 110, 1, 1)
# insert into product (name, description, price, active, restaurant_id) values ('Hot salad will grilled beef', 'Green salad with thin slices cuts of select premium Angus meat', 87.20, 1, 2)
# insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Traditional indian bread with garlic on top', 21, 1, 3)
# insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Chicken cubes prepared with especial curry sauce', 43, 1, 3)
# insert into product (name, description, price, active, restaurant_id) values ('Beef Anchor', 'Big and soft cut, prepared with selected seasoning', 79, 1, 4)
# insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Classic australian cut, served with our especial barbecue sauce', 89, 1, 4)
# insert into product (name, description, price, active, restaurant_id) values ('Sandwich X-Tudo', 'Brazilian sandwich with cheese, hamburger, bacon, egg, salad and especial mayo', 19, 1, 5)
# insert into product (name, description, price, active, restaurant_id) values ('Barbecue Stick', 'came with cassava flour and vinegar sauce', 8, 1, 6)
#
#
# create table kitchen (
#                          id bigint not null auto_increment,
#                          name varchar(255) not null,
#                          primary key (id)
# ) engine=InnoDB
#
#
#
#
#
# create table state (
#                        id bigint not null auto_increment,
#                        name varchar(255) not null,
#                        primary key (id)
# ) engine=InnoDB
# create table city (
#                       id bigint not null auto_increment,
#                       name varchar(255) not null,
#                       state_id bigint not null,
#                       primary key (id)
# ) engine=InnoDB


