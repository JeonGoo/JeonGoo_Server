package com.toy.jeongoo.file.service;

import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.model.MediaType;
import com.toy.jeongoo.file.repository.FileRepository;
import com.toy.jeongoo.file.dto.request.FileInfoRequest;
import com.toy.jeongoo.file.service.upload.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileUploadService fileUploadService;

    @Transactional
    public List<File> upload(FileInfoRequest fileInfoRequest) {
        final List<File> uploadedFileList = createFileList(fileInfoRequest);
        fileRepository.saveAll(uploadedFileList);
        return uploadedFileList;
    }

    private List<File> createFileList(FileInfoRequest fileInfoRequest) {
        List<File> imageFileList = fileInfoRequest.getImageFiles().stream()
                .map(fileUploadService::upload)
                .map(uploadedFile -> new File(uploadedFile, MediaType.IMAGE))
                .collect(Collectors.toList());
        File videoFile = new File(fileUploadService.upload(fileInfoRequest.getVideoFile()), MediaType.VIDEO);

        imageFileList.add(videoFile);
        return imageFileList;
    }
}
