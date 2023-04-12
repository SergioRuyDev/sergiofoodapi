package com.sergioruy.sergiofoodapi.infrastructure.service.storage;

import com.sergioruy.sergiofoodapi.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${sergiofood.storage.local.directory-photos}")
    private Path directoryPhotos;

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            Path filePath = getDirectoryPath(newPhoto.getFileName());

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Was not possible store the file.", e);
        }
    }

    private Path getDirectoryPath(String fileName) {
        return directoryPhotos.resolve(Path.of(fileName));
    }
}
