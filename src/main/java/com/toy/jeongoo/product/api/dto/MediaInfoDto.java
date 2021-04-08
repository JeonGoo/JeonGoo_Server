package com.toy.jeongoo.product.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MediaInfoDto {

    private List<String> imagePaths;
    private String videoPath;

    public MediaInfoDto(List<String> imagePaths, String videoPath) {
        this.imagePaths = imagePaths;
        this.videoPath = videoPath;
    }
}
