package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        if (city.isPresent()) {
//            return ResponseEntity.ok(city.get());
//        }

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody City city) {
        try {
            city = registerCity.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City currentCity = cityRepository.findById(cityId).orElse(null);

            if (currentCity != null) {
                BeanUtils.copyProperties(city, currentCity, "id");

                currentCity = registerCity.save(currentCity);
                return ResponseEntity.ok(currentCity);
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> remove(@PathVariable Long cityId) {
        try {
            registerCity.remove(cityId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
