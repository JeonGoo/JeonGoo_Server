package com.toy.jeongoo.product.repository.purchase;

import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, Long>, PurchasedProductRepositoryCustom {
}
