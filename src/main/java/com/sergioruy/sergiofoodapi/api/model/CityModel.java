package com.sergioruy.sergiofoodapi.api.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityModel {

    private Long id;
    private String name;
    private StateModel state;
}
