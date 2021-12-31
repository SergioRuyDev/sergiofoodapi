package com.sergioruy.sergiofoodapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sergioruy.sergiofoodapi.api.model.mixin.CityMixin;
import com.sergioruy.sergiofoodapi.api.model.mixin.KitchenMixin;
import com.sergioruy.sergiofoodapi.api.model.mixin.RestaurantMixin;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import com.sergioruy.sergiofoodapi.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }

}
