package com.sergioruy.sergiofoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private Long id;
    private String name;
    private String email;
    private String password;
}
