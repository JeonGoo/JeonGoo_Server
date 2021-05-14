package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.ProductRepository;
import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.product.service.purchase.PurchasedProductDeleteService;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDeleteService {

    private final ProductFindService productFindService;
    private final ProductRepository productRepository;
    private final PurchasedProductDeleteService purchasedProductDeleteService;
    private final InterestProductService interestProductService;

    @Transactional
    public Long delete(Long productId) {
        final Product product = productFindService.findProduct(productId);
        purchasedProductDeleteService.deleteAllByProduct(product);
        productRepository.delete(product);
        return product.getId();
    }

    @Transactional
    public long deleteAllByUser(User user) {
        final List<Product> productList = productFindService.findAllProductByUser(user);
        purchasedProductDeleteService.deleteAllByProductListAndPurchasedUser(productList, user);
        interestProductService.deleteAllByInterestedUser(user);
        productRepository.deleteAll(productList);
        return productList.size();
    }
}
