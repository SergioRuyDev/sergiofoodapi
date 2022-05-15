package com.sergioruy.sergiofoodapi.unit.controller;

import com.sergioruy.sergiofoodapi.api.assembler.CityInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.CityModelAssembler;
import com.sergioruy.sergiofoodapi.api.controller.CityController;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import com.sergioruy.sergiofoodapi.unit.mother.CityMother;
import com.sergioruy.sergiofoodapi.unit.mother.StateMother;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterCityService registerCityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private CityModelAssembler cityModelAssembler;

    @MockBean
    private CityInputDisassembler cityInputDisassembler;



    @DisplayName("JUnit test for method getAll Cities")
    @Test
    public void givenListOfCities_WhenGetAllCities_ThenReturnCitiesList() throws Exception {

        //given
        List<CityMother> listOfCities = new ArrayList<>();
        listOfCities.add(CityMother.builder().id(1L).name("Rio de Janeiro").state(com.sergioruy.sergiofoodapi.unit.mother.StateMother.builder().id(2L).name("RJ").build()).build());
        listOfCities.add(CityMother.builder().id(2L).name("Sao paulo").state(StateMother.builder().id(3L).name("SP").build()).build());
//        CityMother city1 = CityMother.builder()
//                .id(1L)
//                .name("Rio")
//                .state(StateMother.builder()
//                        .id(1L)
//                        .name("RJ").build())
//                .build();
//
//        CityMother city2 = CityMother.builder()
//                .id(2L)
//                .name("Sao")
//                .state(StateMother.builder()
//                        .id(2L)
//                        .name("SP").build())
//                .build();



        given(cityRepository.findAll()).willReturn(anyList());
//        List<City> cityMotherList = registerCityService.getAllCities();


        //when
        ResultActions response = mockMvc.perform(get("/cities"));


        //then
//        assertThat(listOfCities).isNotNull();
//        assertThat(listOfCities.size()).isEqualTo(2);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(listOfCities.size())));
    }
}
