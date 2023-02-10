package com.sergioruy.sergiofoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @NotNull
    private AddressInput address;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemOrderInput> items;
}
