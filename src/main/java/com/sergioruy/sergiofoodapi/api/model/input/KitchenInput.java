package com.sergioruy.sergiofoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class KitchenInput {

    @NotBlank
    private String name;
}
