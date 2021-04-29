package com.toy.jeongoo.product.repository.purchase;

import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.user.model.User;

import java.util.List;

public interface PurchasedProductRepositoryCustom {
    List<PurchasedProduct> findAllByPurchasedUserWithProductAndPurchasedUser();

    List<PurchasedProduct> findAllByPurchasedUserWithProduct(User purchasedUser);

    List<PurchasedProduct> findAllBySaleUserWithProduct(User saleUser);
}
