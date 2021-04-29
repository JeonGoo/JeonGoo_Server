package com.toy.jeongoo.product.service;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.product.repository.purchase.PurchasedProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPurchaseService {

    private final ProductFindService productFindService;
    private final UserFindService userFindService;
    private final PurchasedProductRepository purchasedProductRepository;

    @Transactional
    public Long purchase(Long productId, Long userId) {
        final Product product = productFindService.findProduct(productId);
        final User user = userFindService.findUser(userId);
        final PurchasedProduct purchasedProduct = new PurchasedProduct(product, user);
        product.soldOut();

        purchasedProductRepository.save(purchasedProduct);
        return purchasedProduct.getId();
    }
}
