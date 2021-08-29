create table `order` (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    tax_delivery decimal(10,2) not null,
    total_amount decimal(10,2) not null,

    restaurant_id bigint not null,
    user_customer_id bigint not null,
    payment_method_id bigint not null,

    address_city_id bigint(20) not null,
    address_postalcode varchar(9) not null,
    address_street varchar(100) not null,
    address_number varchar(20) not null,
    address_complement varchar(60) null,
    address_district varchar(60) not null,

    status varchar(10) not null,
    date_created datetime not null,
    date_confirmed datetime null,
    date_canceled datetime null,
    date_delivered datetime null,

    primary key (id),

    constraint fk_order_address_city foreign key (address_city_id) references city (id),
    constraint fk_order_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_order_user_customer foreign key (user_customer_id) references user (id),
    constraint fk_order_payment_method foreign key (payment_method_id) references payment_method (id)
) engine=InnoDB default charset=utf8mb4;

create table item_ordered (
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unit_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    observations varchar(255) null,
    order_id bigint not null,
    product_id bigint not null,

    primary key (id),
    unique key uk_item_ordered_product (order_id, product_id),

    constraint fk_item_ordered_order foreign key (order_id) references `order` (id),
    constraint fk_item_ordered_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8mb4;