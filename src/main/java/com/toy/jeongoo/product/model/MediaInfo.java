package com.toy.jeongoo.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MediaInfo {

    private List<String> imagePaths;
    private String videoPath;

    public MediaInfo(List<String> imagePaths, String videoPath) {
        this.imagePaths = imagePaths;
        this.videoPath = videoPath;
    }
}
