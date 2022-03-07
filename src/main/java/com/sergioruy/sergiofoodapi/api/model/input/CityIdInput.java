package com.sergioruy.sergiofoodapi.api.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdInput {

    @NotNull
    private Long id;

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    @Valid
    @NotNull
    private String name;
}
