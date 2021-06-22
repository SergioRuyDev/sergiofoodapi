package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultKitchenMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchens = applicationContext.getBean(KitchenRepository.class);

        List<Kitchen> allKitchens = kitchens.list();

        for (Kitchen kitchen : allKitchens) {
            System.out.println(kitchen.getName());
        }
    }
}
