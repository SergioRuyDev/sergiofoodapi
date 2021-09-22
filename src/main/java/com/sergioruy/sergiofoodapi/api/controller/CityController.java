package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.exceptionHandler.Problem;
import com.sergioruy.sergiofoodapi.domain.exception.BusinessException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.StateNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCity;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public City search(@PathVariable Long cityId) {
        return registerCity.findOrFail(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city) {
        try {
            return registerCity.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public City update(@PathVariable Long cityId, @RequestBody City city) {
        City currentCity = registerCity.findOrFail(cityId);

        BeanUtils.copyProperties(city, currentCity, "id");

        try {
            return registerCity.save(currentCity);
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
