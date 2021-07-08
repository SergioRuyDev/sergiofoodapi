package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterCityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.find(stateId);

        if (state == null) {
            throw new EntityNotFoundException(String.format("State with code %d not found.", stateId));
        }

        city.setState(state);
        return cityRepository.save(city);
    }
}
