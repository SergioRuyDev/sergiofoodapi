package com.sergioruy.sergiofoodapi.unit.mother;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityMother {

    private Long id;
    private String name;
    private StateMother state;
}
