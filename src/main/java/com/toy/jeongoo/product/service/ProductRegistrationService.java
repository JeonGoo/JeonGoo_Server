package com.toy.jeongoo.product.service;

import com.toy.jeongoo.file.dto.request.FileInfoRequest;
import com.toy.jeongoo.file.model.File;
import com.toy.jeongoo.file.service.FileService;
import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRegistrationService {

    private final ProductRepository productRepository;
    private final UserFindService userFindService;
    private final FileService fileService;

    @Transactional
    public Long register(ProductBasicInfoRequest productBasicInfo, FileInfoRequest fileInfo, Long userId) {
        final User user = userFindService.findUser(userId);
        final List<File> uploadedFileList = fileService.upload(fileInfo);
        final Product product = new Product(productBasicInfo.getName(), productBasicInfo.getPrice(), productBasicInfo.getSerialNumber(),
                productBasicInfo.getDescription(), productBasicInfo.getUseStatus(), user, uploadedFileList);
        productRepository.save(product);

        return product.getId();
    }
}
