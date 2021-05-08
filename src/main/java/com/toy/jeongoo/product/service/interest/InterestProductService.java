package com.toy.jeongoo.product.service.interest;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.repository.interest.InterestProductRepository;
import com.toy.jeongoo.product.service.ProductFindService;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterestProductService {

    private final InterestProductRepository interestProductRepository;
    private final ProductFindService productFindService;
    private final UserFindService userFindService;

    @Transactional
    public Long registerInterestProduct(Long productId, Long userId) {
        final Product product = productFindService.findProduct(productId);
        final User user = userFindService.findUser(userId);
        final InterestProduct interestProduct = new InterestProduct(product, user);

        interestProductRepository.save(interestProduct);
        return interestProduct.getId();
    }
}
