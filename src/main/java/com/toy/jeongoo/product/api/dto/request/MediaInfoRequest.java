package com.toy.jeongoo.product.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class MediaInfoRequest {

    private List<MultipartFile> imageFiles;
    private MultipartFile videoFile;

    public MediaInfoRequest(List<MultipartFile> imageFiles, MultipartFile videoFile) {
        this.imageFiles = imageFiles;
        this.videoFile = videoFile;
    }
}
