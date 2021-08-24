create table order (
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



)