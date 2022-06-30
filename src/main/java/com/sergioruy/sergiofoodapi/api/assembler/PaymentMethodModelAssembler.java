package com.sergioruy.sergiofoodapi.api.assembler;

import com.sergioruy.sergiofoodapi.api.model.PaymentMethodModel;
import com.sergioruy.sergiofoodapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodModel.class);
    }

    public List<PaymentMethodModel> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
