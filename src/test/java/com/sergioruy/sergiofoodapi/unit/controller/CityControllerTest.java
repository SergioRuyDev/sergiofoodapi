package com.sergioruy.sergiofoodapi.unit.controller;

import com.sergioruy.sergiofoodapi.api.assembler.CityInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.CityModelAssembler;
import com.sergioruy.sergiofoodapi.api.controller.CityController;
import com.sergioruy.sergiofoodapi.api.model.CityModel;
import com.sergioruy.sergiofoodapi.api.model.StateModel;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterCityService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
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
        List<CityModel> listOfCities = new ArrayList<>();
        listOfCities.add(new CityModel(1L, "Rio de Janeiro", new StateModel(1L, "RJ")));
        listOfCities.add(new CityModel(2L, "Sao paulo", new StateModel(2L, "SP")));
        given(registerCityService.getAllCities());


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
