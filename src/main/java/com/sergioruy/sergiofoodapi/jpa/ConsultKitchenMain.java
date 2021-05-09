package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultKitchenMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegistryKitchen registryKitchen = applicationContext.getBean(RegistryKitchen.class);

        List<Kitchen> kitchens = registryKitchen.kitchenList();

        for (Kitchen kitchen : kitchens) {
            System.out.println(kitchen.getName());
        }
    }
}
