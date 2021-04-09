package com.toy.jeongoo.upload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {
    String upload(MultipartFile file);
}
