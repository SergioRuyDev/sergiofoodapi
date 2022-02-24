package com.sergioruy.sergiofoodapi.api.controller;

import com.sergioruy.sergiofoodapi.api.assembler.PaymentMethodInputDisassembler;
import com.sergioruy.sergiofoodapi.api.assembler.PaymentMethodModelAssembler;
import com.sergioruy.sergiofoodapi.api.model.PaymentMethodModel;
import com.sergioruy.sergiofoodapi.api.model.input.PaymentMethodInput;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import com.sergioruy.sergiofoodapi.domain.repository.PaymentMethodRepository;
import com.sergioruy.sergiofoodapi.domain.service.RegisterPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModel add(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
            PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);

            return paymentMethodModelAssembler.toModel(registerPaymentMethodService.save(paymentMethod));
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModel update(@PathVariable Long paymentMethodId, @RequestBody PaymentMethodInput paymentMethodInput) {
        PaymentMethod currentPayment = registerPaymentMethodService.findOrFail(paymentMethodId);

        paymentMethodInputDisassembler.copyToDomainObject(paymentMethodInput, currentPayment);

        return paymentMethodModelAssembler.toModel(registerPaymentMethodService.save(currentPayment));
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long paymentMethodId) {
        registerPaymentMethodService.delete(paymentMethodId);
    }

}
