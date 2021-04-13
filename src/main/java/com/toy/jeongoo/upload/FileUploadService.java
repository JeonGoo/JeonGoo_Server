package com.toy.jeongoo.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadService {
    String upload(MultipartFile file);
}
