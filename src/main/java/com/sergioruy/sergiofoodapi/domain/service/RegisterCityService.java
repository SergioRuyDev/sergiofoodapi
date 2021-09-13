package com.sergioruy.sergiofoodapi.domain.service;

import com.sergioruy.sergiofoodapi.domain.exception.EntityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RegisterCityService {

    private static final String MSG_CITY_IS_IN_USE = "City of code %d is in use and cannot be removed";

    private static final String MSG_CITY_NOT_FOUND = "Not exist City registered with the code %d";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService stateService;

    public City save(City city) {
        Long stateId = city.getState().getId();

        State state = stateService.findOrFail(stateId);

        city.setState(state);

        return cityRepository.save(city);
    }

    public void remove(Long cityId) {
        try {
            cityRepository.deleteById(cityId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, cityId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityUsedException(String.format(MSG_CITY_IS_IN_USE, cityId));
        }
    }

    public City findOrFail(Long cityId){
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_CITY_NOT_FOUND, cityId)));
    }
}
