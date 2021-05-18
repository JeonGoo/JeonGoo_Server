package com.toy.jeongoo.file.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploader fileUploader;

    public FileUploadServiceImpl(S3FileUploader s3FileUploader) {
        this.fileUploader = s3FileUploader;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        return fileUploader.upload(file);
    }
}
