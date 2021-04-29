package com.toy.jeongoo.product.service.purchase;

import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.product.repository.purchase.PurchasedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasedProductFindService {

    private final PurchasedProductRepository purchasedProductRepository;

    @Transactional(readOnly = true)
    public List<PurchasedProduct> findPurchasedProducts() {
        return purchasedProductRepository.findAllWithProductAndPurchasedUser();
    }
}
