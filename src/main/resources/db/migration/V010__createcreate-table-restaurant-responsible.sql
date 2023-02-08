create table restaurant_user_responsible (
                                           restaurant_id bigint not null,
                                           user_id bigint not null,

                                           primary key (restaurant_id, user_id)
) engine=InnoDB default charset=utf8mb4;

alter table restaurant_user_responsible add constraint fk_rest_user_responsible
    foreign key (user_id) references user (id);

alter table restaurant_user_responsible add constraint fk_rest_restaurant_responsible
    foreign key (restaurant_id) references restaurant (id);
