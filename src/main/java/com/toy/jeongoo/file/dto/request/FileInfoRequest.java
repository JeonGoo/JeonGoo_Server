package com.toy.jeongoo.file.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class FileInfoRequest {

    private List<MultipartFile> imageFiles;
    private MultipartFile videoFile;

    public FileInfoRequest(List<MultipartFile> imageFiles, MultipartFile videoFile) {
        this.imageFiles = imageFiles;
        this.videoFile = videoFile;
    }
}
