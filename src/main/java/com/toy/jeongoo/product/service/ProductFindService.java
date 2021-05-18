package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Product findProduct(Long productId) {
        return findProductById(productId);
    }

    @Transactional
    public Product showProductDetail(Long productId) {
        final Product product = findProductById(productId);
        product.hit();
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductPage(Pageable pageable) {
        return productRepository.findAllPageWithUserAndInterestProducts(pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductPageByUser(Long userId, Pageable pageable) {
        final User user = userFindService.findUser(userId);
        return productRepository.findAllPageByUserWithInterestProducts(user, pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProductByUser(User user) {
        return productRepository.findAllByUserWithInterestProducts(user);
    }
  
    @Transactional(readOnly = true)
    public List<Product> showAllSaleProductsPage(Pageable pageable) {
        return productRepository.findAllSaleProductsPage(pageable);
    }

    private Product findProductById(Long productId) {
        return productRepository.findByIdWithUserAndInterestProducts(productId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found product. input id: %d", productId)));
    }
}
