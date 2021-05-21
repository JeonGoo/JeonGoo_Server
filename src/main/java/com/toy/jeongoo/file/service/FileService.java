package com.toy.jeongoo.file.service;

import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.model.FileType;
import com.toy.jeongoo.file.repository.FileRepository;
import com.toy.jeongoo.file.service.upload.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    public List<File> upload(List<MultipartFile> imageFiles, MultipartFile videoFile) {
        final List<File> uploadedFileList = createFileList(imageFiles, videoFile);
        fileRepository.saveAll(uploadedFileList);
        return uploadedFileList;
    }

    private List<File> createFileList(List<MultipartFile> images, MultipartFile video) {
        List<File> imageFileList = images.stream()
                .map(this::findUploadFilePath)
                .map(uploadedFile -> new File(uploadedFile, FileType.IMAGE))
                .collect(Collectors.toList());
        File videoFile = new File(findUploadFilePath(video), FileType.VIDEO);

        imageFileList.add(videoFile);
        return imageFileList;
    }

    private String findUploadFilePath(MultipartFile file) {
        String uploadedFilePath = "";
        try {
            uploadedFilePath = fileUploadService.upload(file);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return uploadedFilePath;
    }
}
