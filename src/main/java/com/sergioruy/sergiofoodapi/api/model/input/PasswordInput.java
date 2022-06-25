package com.sergioruy.sergiofoodapi.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PasswordInput {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
