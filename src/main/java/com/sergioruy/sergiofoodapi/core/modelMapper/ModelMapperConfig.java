package com.sergioruy.sergiofoodapi.core.modelMapper;

import com.sergioruy.sergiofoodapi.api.model.AddressModel;
import com.sergioruy.sergiofoodapi.api.model.input.ItemOrderInput;
import com.sergioruy.sergiofoodapi.domain.model.Address;
import com.sergioruy.sergiofoodapi.domain.model.ItemOrdered;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemOrderInput.class, ItemOrdered.class)
                .addMappings(mapper -> mapper.skip(ItemOrdered::setId));

        var addressToAddressModelTypeMap = modelMapper.createTypeMap(
                Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String>addMapping(
                addressSrc -> addressSrc.getCity().getState().getName(),
                (addressModelDest, value) -> addressModelDest.getCity().setState(value));

        return modelMapper;
    }
}


//Customize the properties
//    public ModelMapper modelMapper() {
//        var modelMapper = new ModelMapper();
//
//        modelMapper.createTypeMap(Restaurant.class, RestaurantModel.class)
//                .addMapping(Restaurant::getTaxDelivery, RestaurantModel::setPriceDelivery);
//        return modelMapper;
//    }
