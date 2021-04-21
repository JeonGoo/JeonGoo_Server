package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.api.dto.request.ProductCertificationRequest;
import com.toy.jeongoo.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCertificationService {

    private final ProductFindService productFindService;

    @Transactional
    public Long certify(Long productId, ProductCertificationRequest certificationRequest) {
        final Product product = productFindService.findProduct(productId);
        product.certify(certificationRequest.getProductGrade());
        return product.getId();
    }

    @Transactional
    public Long failed(Long productId, ProductCertificationFailedRequest certificationFailedRequest) {
        final Product product = productFindService.findProduct(productId);
        product.certifyFailed(certificationFailedRequest.getCertificationFailedReason());
        return product.getId();
    }
}
