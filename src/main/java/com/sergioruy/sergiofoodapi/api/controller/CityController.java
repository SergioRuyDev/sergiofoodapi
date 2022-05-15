package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.CityInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.CityModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.CityMother;
import com.sergioruy.sergiofoodapi.api.model.input.CityInput;
import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.StateNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCity;

    @Autowired
    private CityModelAssembler cityModelAssembler;

    @Autowired
    private CityInputDisassembler cityInputDisassembler;

    @GetMapping
    public List<CityMother> list() {
        return cityModelAssembler.toCollectionModel(registerCity.getAllCities());
    }

    @GetMapping("/{cityId}")
    public CityMother search(@PathVariable Long cityId) {
        City city = registerCity.findOrFail(cityId);

        return cityModelAssembler.toModel(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityMother add(@RequestBody CityInput cityInput) {
        try {
            City city = cityInputDisassembler.toDomainObject(cityInput);

            return cityModelAssembler.toModel(registerCity.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public CityMother update(@PathVariable Long cityId, @RequestBody CityInput cityInput) {
        try {
            City currentCity = registerCity.findOrFail(cityId);

            cityInputDisassembler.copyToDomainObject(cityInput, currentCity);

//            BeanUtils.copyProperties(city, currentCity, "id");

            return cityModelAssembler.toModel(registerCity.save(currentCity));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cityId) {
        registerCity.remove(cityId);
    }

}
