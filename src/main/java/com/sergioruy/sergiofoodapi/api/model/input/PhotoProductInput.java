package com.sergioruy.sergiofoodapi.api.model.input;

import com.sergioruy.sergiofoodapi.core.validation.FileContentType;
import com.sergioruy.sergiofoodapi.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoProductInput {

    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    private MultipartFile file;

    @NotBlank
    private String description;
}
