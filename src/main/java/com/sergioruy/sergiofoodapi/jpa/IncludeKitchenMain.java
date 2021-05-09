package com.sergioruy.sergiofoodapi.jpa;

import com.sergioruy.sergiofoodapi.SergiofoodapiApplication;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class IncludeKitchenMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(SergiofoodapiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RegistryKitchen registryKitchen = applicationContext.getBean(RegistryKitchen.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brazilian");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setName("Japanese");

        kitchen1 = registryKitchen.add(kitchen1);
        kitchen2 = registryKitchen.add(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
    }
}
