package com.sergioruy.sergiofoodapi.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderFilter {

    private Long customerId;
    private Long restaurantId;
    private OffsetDateTime createDateStart;
    private OffsetDateTime createDateEnd;
}
