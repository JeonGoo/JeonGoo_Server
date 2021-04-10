package com.toy.jeongoo.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MediaInfo {

    private String imagePaths;
    private String videoPath;

    public MediaInfo(String imagePaths, String videoPath) {
        this.imagePaths = imagePaths;
        this.videoPath = videoPath;
    }
}
