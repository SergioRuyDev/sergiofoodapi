package com.sergioruy.sergiofoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergioruy.sergiofoodapi.domain.model.Address;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.model.Product;

import java.time.OffsetDateTime;
import java.util.List;

public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private OffsetDateTime dateRegister;

    @JsonIgnore
    private OffsetDateTime dateUpdate;

    @JsonIgnore
    private List<PaymentMethod> paymentMethods;

    @JsonIgnore
    private List<Product> products;
}
