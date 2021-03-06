package com.toy.jeongoo.product.service;

import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.service.FileService;
import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUpdateService {

    private final ProductFindService productFindService;
    private final FileService fileService;

    @Transactional
    public Long update(Long productId, ProductBasicInfoRequest productBasicInfo, List<MultipartFile> imageFiles, MultipartFile videoFile) {
        final Product product = productFindService.findProduct(productId);
        final List<File> uploadedFileList = fileService.upload(imageFiles, videoFile);
        product.update(productBasicInfo.getName(), productBasicInfo.getPrice(), productBasicInfo.getSerialNumber(),
                productBasicInfo.getDescription(), productBasicInfo.getUseStatus(), uploadedFileList);

        return product.getId();
    }

    @Transactional
    public Long updateGrade(Long productId, ProductGradeUpdateRequest gradeUpdateRequest) {
        final Product product = productFindService.findProduct(productId);
        product.changeGrade(gradeUpdateRequest.getProductGrade());
        return product.getId();
    }
}
