package com.toy.jeongoo.product.service.purchase;

import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.product.repository.purchase.PurchasedProductRepository;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchasedProductFindService {

    private final PurchasedProductRepository purchasedProductRepository;
    private final UserFindService userFindService;

    public List<PurchasedProduct> findPurchasedProducts() {
        return purchasedProductRepository.findAllByPurchasedUserWithProductAndPurchasedUser();
    }

    public List<PurchasedProduct> findAllPurchasedProductByPurchasedUser(Long purchasedUserId) {
        final User purchasedUser = userFindService.findUser(purchasedUserId);
        return purchasedProductRepository.findAllByPurchasedUserWithProduct(purchasedUser);
    }
}
