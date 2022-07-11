package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.input.PermissionInput;
import com.sergioruy.sergiofoodapi.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Permission toDomainObject(PermissionInput permissionInput) {
        return modelMapper.map(permissionInput, Permission.class);
    }

    public void copyToDomainObject(PermissionInput permissionInput, Permission permission) {
        modelMapper.map(permissionInput, permission);
    }
}
