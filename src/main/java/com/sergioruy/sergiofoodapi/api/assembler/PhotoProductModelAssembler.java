package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.PhotoProductModel;
import com.sergioruy.sergiofoodapi.domain.model.PhotoProduct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoProductModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PhotoProductModel toModel(PhotoProduct photo) {
        return modelMapper.map(photo, PhotoProductModel.class);
    }

}
