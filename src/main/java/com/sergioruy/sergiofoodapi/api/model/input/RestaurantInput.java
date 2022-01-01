package com.sergioruy.sergiofoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantInput {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxDelivery;

    @Valid
    @NotNull
    private KitchenIdInput kitchen;
}
