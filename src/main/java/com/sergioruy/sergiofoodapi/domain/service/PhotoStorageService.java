package com.sergioruy.sergiofoodapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface PhotoStorageService {

    void store(NewPhoto newPhoto);

    @Builder
    @Getter
    class NewPhoto {
        private String fileName;
        private InputStream inputStream;
    }
}
