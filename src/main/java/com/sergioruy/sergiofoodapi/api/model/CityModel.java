package com.sergioruy.sergiofoodapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CityModel {

    private Long id;
    private String name;
    private StateModel state;
}
