package com.toy.jeongoo.product.service.purchase;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.repository.purchase.PurchasedProductRepository;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasedProductDeleteService {

    private final PurchasedProductRepository purchasedProductRepository;

    @Transactional
    public long deleteAllByProduct(Product product) {
        return purchasedProductRepository.deleteAllByProduct(product);
    }

    @Transactional
    public long deleteAllByProductListAndPurchasedUser(List<Product> productList, User user) {
        return purchasedProductRepository.deleteAllByProductListAndPurchasedUser(productList, user);
    }
}
