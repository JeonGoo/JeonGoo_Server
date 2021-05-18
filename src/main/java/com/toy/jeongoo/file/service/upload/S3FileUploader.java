package com.toy.jeongoo.file.service.upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.toy.jeongoo.file.properties.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader {

    private final AwsS3Properties s3Properties;
    private final AmazonS3Client amazonS3Client;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        final File uploadFile = convert(multipartFile);
        return upload(uploadFile);
    }

    private String upload(File uploadFile) {
        final String fileName = s3Properties.getDirName() + "/" + uploadFile.getName();
        final String uploadFilePath = uploadOnS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadFilePath;
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("Delete file");
            return;
        }
        log.info("Delete file fail!");
    }

    private String uploadOnS3(final File uploadFile, final String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(s3Properties.getBucket(), fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private File convert(MultipartFile file) throws IOException {
        final File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return convertFile;
        }
        throw new IllegalArgumentException(String.format("fail convert from MultipartFile to File. file name: %s", file.getOriginalFilename()));
    }
}
