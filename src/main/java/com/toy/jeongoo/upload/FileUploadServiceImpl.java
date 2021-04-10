package com.toy.jeongoo.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String upload(MultipartFile file) {
        return "new file path";
    }
}
