package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.KitchenModel;
import com.sergioruy.sergiofoodapi.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModel toModel(Kitchen kitchen) {
        return modelMapper.map(kitchen, KitchenModel.class);
    }


    public List<KitchenModel> toCollectionModel(List<Kitchen> kitchens) {
        return kitchens.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
