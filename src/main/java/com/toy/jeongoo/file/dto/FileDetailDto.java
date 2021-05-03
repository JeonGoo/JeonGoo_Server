package com.toy.jeongoo.file.dto;

import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.model.FileType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileDetailDto {

    private String filePath;
    private FileType fileType;

    public FileDetailDto(File file) {
        this.filePath = file.getPath();
        this.fileType = file.getFileType();
    }
}
