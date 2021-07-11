package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultCityMain {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cities = applicationContext.getBean(CityRepository.class);

        List<City> allCities = cities.findAll();

        for (City city : allCities) {
            System.out.printf("%s - %s\n", city.getName(), city.getState().getName());
        }

    }
}
