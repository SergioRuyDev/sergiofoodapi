package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.model.Estate;
import com.sergioruy.sergiofoodapi.domain.repository.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estates")
public class EstateController {

    @Autowired
    public EstateRepository estateRepository;

    List<Estate> list() {
        return
    }
}
