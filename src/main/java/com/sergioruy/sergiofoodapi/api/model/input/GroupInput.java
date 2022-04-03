package com.sergioruy.sergiofoodapi.api.model.input;

import javax.validation.constraints.NotBlank;

public class GroupInput {

    @NotBlank
    private String name;
}
