package com.sergioruy.sergiofoodapi.unit.service;

import com.sergioruy.sergiofoodapi.domain.exception.CityNotFoundException;
import com.sergioruy.sergiofoodapi.domain.exception.EntityUsedException;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.State;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import com.sergioruy.sergiofoodapi.domain.service.RegisterStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterCityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private RegisterStateService stateService;

    @InjectMocks
    private RegisterCityService cityService;

    private City city;

    private State state;

    @BeforeEach
    public void setUp() {
        state = new State();
        state.setId(1L);
        state.setName("New York");

        city = new City();
        city.setId(1L);
        city.setName("Manhattan");
    }

    @DisplayName("Unit test for save City method")
    @Transactional
    @Test
    void givenCityObject_whenSaveCity_thenReturnCityObject() {
        Long stateId = 1L;
        State state = new State();
        state.setId(stateId);

        City city = new City();
        city.setName("Manhattan");
        city.setState(state);
        when(stateService.findOrFail(1L)).thenReturn(state);
        when(cityRepository.save(city)).thenReturn(city);

        City savedCity = cityService.save(city);

        // Assert
        verify(stateService, times(1)).findOrFail(state.getId());
        verify(cityRepository, times(1)).save(city);
        assertEquals(state, savedCity.getState());
        assertEquals("Manhattan", savedCity.getName());
    }

    @DisplayName("Unit test City Find or Fail method and throws Exception")
    @Test
    void given_cityWithNullId_whenFindOrFailCall_thenThrowsException() {
        City city1 = new City();
        city1.setId(null);
        assertThrows(CityNotFoundException.class, () -> {
            cityService.findOrFail(city1.getId());
        });
    }

    @DisplayName("Unit test for delete city by Id")
    @Test
    void givenCityId_whenDeleteCity_thenDoNothing() {
        willDoNothing().given(cityRepository).deleteById(city.getId());

        cityService.remove(city.getId());
        //then - verify the result
        verify(cityRepository, times(1)).deleteById(city.getId());
    }

    @DisplayName("Unit test for delete City by Id in the negative scenario")
    @Test
    void delete_NonExistentCityId_CityNotFoundException() {
        // given
        doThrow(EmptyResultDataAccessException.class).when(cityRepository).deleteById(city.getId());

        // when + then
        assertThrows(CityNotFoundException.class, () -> cityService.remove(city.getId()));
    }

    @DisplayName("Unit test for delete City by Id in the negative scenario, throw DataIntegrationException")
    @Test
    void delete_CityInUse_EntityUsedException() {
        // given
        doThrow(DataIntegrityViolationException.class).when(cityRepository).deleteById(city.getId());

        // when + then
        EntityUsedException ex = assertThrows(EntityUsedException.class, () -> cityService.remove(city.getId()));
        assertEquals(String.format(RegisterCityService.MSG_CITY_IS_IN_USE, city.getId()), ex.getMessage());
    }
}
