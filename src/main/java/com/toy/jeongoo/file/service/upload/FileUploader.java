package com.toy.jeongoo.file.service.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {
    String upload(MultipartFile file) throws IOException;
}
