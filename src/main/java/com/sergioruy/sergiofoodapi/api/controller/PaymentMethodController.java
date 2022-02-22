package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.PaymentMethodInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.PaymentMethodModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.PaymentMethodModel;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.repository.PaymentMethodRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private RegisterPaymentMethodService registerPaymentMethodService;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Autowired
    private PaymentMethodInputDisassembler paymentMethodInputDisassembler;


    @GetMapping
    public List<PaymentMethodModel> list() {
        return paymentMethodModelAssembler.toCollectionModel(paymentMethodRepository.findAll());
    }

    @GetMapping("{paymentMethodId}")
    public PaymentMethodModel search(@PathVariable Long paymentMethodId) {
        PaymentMethod paymentMethod = registerPaymentMethodService.findOrFail(paymentMethodId);

        return paymentMethodModelAssembler.toModel(paymentMethod);
    }


}
