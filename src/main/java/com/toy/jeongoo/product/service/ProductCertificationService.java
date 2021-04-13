package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.api.dto.request.ProductCertificationRequest;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductCertificationService {

    private final ProductRepository productRepository;

    @Transactional
    public Long certify(Long productId, ProductCertificationRequest certificationRequest) {
        final Product product = findProductById(productId);
        product.certify(certificationRequest.getProductGrade());
        return product.getId();
    }

    @Transactional
    public Long failed(Long productId, ProductCertificationFailedRequest certificationFailedRequest) {
        final Product product = findProductById(productId);
        product.certifyFailed(certificationFailedRequest.getCertificationFailedReason());
        return product.getId();
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found product. input Id: %d", productId)));
    }
}
