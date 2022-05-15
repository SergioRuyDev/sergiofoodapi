package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.CityMother;
import com.sergioruy.sergiofoodapi.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CityMother toModel(City city) {
        return modelMapper.map(city, CityMother.class);
    }

    public List<CityMother> toCollectionModel(List<City> cities) {
        return cities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
