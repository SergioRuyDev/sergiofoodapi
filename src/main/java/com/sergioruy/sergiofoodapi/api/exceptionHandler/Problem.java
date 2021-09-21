package com.sergioruy.sergiofoodapi.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {

    private LocalDateTime dataTime;
    private String message;
}
