package com.toy.jeongoo.upload;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@Component
public class S3FileUploader implements FileUploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.imagePath}")
    private String imagePath;

    @Value("${cloud.aws.s3.bucket.voicePath}")
    private String voicePath;

    private final AmazonS3Client amazonS3Client;

    public S3FileUploader(final AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    @Override
    public String upload(MultipartFile uploadFile) {
        String origName = uploadFile.getOriginalFilename();
        String url;
        try {
            //확장자
            final String ext = origName.substring(origName.lastIndexOf('.'));
            //파일이름 암호화
            //final String saveFileName = getUuid() + ext;
            //파일 객체 생성
            File file = new File(System.getProperty("user.dir") + "/" + origName);
            //파일 변환
            uploadFile.transferTo(file);
            //S3 파일 업로드
            uploadOnS3(origName, file);
            //주소 할당
            if (ext.equals(".3gp"))
                url = voicePath + origName;
            else
                url = imagePath + origName;
            //파일 삭제
            file.delete();
        } catch (StringIndexOutOfBoundsException e) {
            //파일이 없을 경우 예외 처리
            url = null;
        }
        return url;
    }


    private static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    private void uploadOnS3(final String fileName, final File file) {
        String imageFileName;
        String voiceFileName;
        String ext;
        final PutObjectRequest request;

        //AWS S3 전송 객체 생성
        final TransferManager transferManager = new TransferManager(this.amazonS3Client);
        //요청 객체 생성
        imageFileName = "dataset/image/" + fileName;
        voiceFileName = "dataset/voice/" + fileName;

        //확장자
        ext = fileName.substring(fileName.lastIndexOf("."));

        if (ext.equals(".jpg"))
            request = new PutObjectRequest(bucket, imageFileName, file);
        else
            request = new PutObjectRequest(bucket, voiceFileName, file);
        //업로드 시도
        final Upload upload = transferManager.upload(request);

        try {
            //완료 확인
            upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            log.error(amazonClientException.getMessage());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
