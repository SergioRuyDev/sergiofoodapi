package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.service.FluxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class FluxOrderController {

    @Autowired
    FluxOrderService fluxOrderService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        fluxOrderService.confirm(orderCode);
    }

    @PutMapping("/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivered(@PathVariable String orderCode) {
        fluxOrderService.delivered(orderCode);
    }

    @PutMapping("/canceled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void canceled(@PathVariable String orderCode) {
        fluxOrderService.canceled(orderCode);
    }
}
