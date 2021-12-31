package com.sergioruy.sergiofoodapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sergioruy.sergiofoodapi.domain.model.State;

public abstract class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;

}
