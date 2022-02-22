package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.PaymentMethodModel;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodModelAssembler {

    private ModelMapper modelMapper;

    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodModel.class);
    }

    public List<PaymentMethodModel> toCollectionModel(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
