package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductDeleteService {

    private final ProductFindService productFindService;
    private final ProductRepository productRepository;

    @Transactional
    public Long delete(Long productId) {
        final Product product = productFindService.findProduct(productId);
        productRepository.delete(product);
        return product.getId();
    }
}
