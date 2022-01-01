package com.sergioruy.sergiofoodapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sergioruy.sergiofoodapi.api.model.mixin.CityMixin;
import com.sergioruy.sergiofoodapi.api.model.mixin.KitchenMixin;
import com.sergioruy.sergiofoodapi.domain.model.City;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }

}
