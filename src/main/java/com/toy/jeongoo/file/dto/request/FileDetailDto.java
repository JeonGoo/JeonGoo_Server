package com.toy.jeongoo.file.dto.request;

import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.model.MediaType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileDetailDto {

    private String filePath;
    private MediaType mediaType;

    public FileDetailDto(File file) {
        this.filePath = file.getPath();
        this.mediaType = file.getMediaType();
    }
}
