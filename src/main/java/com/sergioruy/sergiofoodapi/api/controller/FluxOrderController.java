package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.domain.service.FluxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderId}")
public class FluxOrderController {

    @Autowired
    FluxOrderService fluxOrderService;

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        fluxOrderService.confirm(orderId);
    }

    @PutMapping("/delivered")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivered(@PathVariable Long orderId) {
        fluxOrderService.delivered(orderId);
    }

    @PutMapping("/canceled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void canceled(@PathVariable Long orderId) {
        fluxOrderService.canceled(orderId);
    }
}
