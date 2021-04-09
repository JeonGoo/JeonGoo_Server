package com.toy.jeongoo.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploader fileUploader;

    public String upload(MultipartFile file) {
        return fileUploader.upload(file);
    }
}
