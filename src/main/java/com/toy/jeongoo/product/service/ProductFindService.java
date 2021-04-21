package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductFindService {

    private final ProductRepository productRepository;
    private final UserFindService userFindService;

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        return findProductById(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductByUser(Long userId) {
        final User user = userFindService.findUser(userId);
        return productRepository.findAllByUser(user);
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found product. input id: %d", productId)));
    }
}
