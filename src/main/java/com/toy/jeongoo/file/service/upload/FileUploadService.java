package com.toy.jeongoo.file.service.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploader fileUploader;

    public String upload(MultipartFile file) throws IOException {
        return fileUploader.upload(file);
    }
}
