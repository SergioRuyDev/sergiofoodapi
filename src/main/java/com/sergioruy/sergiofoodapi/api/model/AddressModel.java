package com.sergioruy.sergiofoodapi.api.model;

import com.sergioruy.sergiofoodapi.api.model.mixin.CitySummaryModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CitySummaryModel city;

}
