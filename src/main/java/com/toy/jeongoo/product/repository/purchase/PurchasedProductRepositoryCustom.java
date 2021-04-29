package com.toy.jeongoo.product.repository.purchase;

import com.toy.jeongoo.product.model.purchased.PurchasedProduct;

import java.util.List;

public interface PurchasedProductRepositoryCustom {
    List<PurchasedProduct> findAllWithProductAndPurchasedUser();
}
