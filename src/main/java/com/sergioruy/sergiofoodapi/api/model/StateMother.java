package com.sergioruy.sergiofoodapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class StateMother {

    private Long id;
    private String name;
}
