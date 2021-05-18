package com.toy.jeongoo.file.service.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface FileUploader {
    String upload(MultipartFile file) throws IOException;
}
