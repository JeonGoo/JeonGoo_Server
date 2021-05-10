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

import java.util.List;
import java.util.NoSuchElementException;

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

    @Transactional(readOnly = true)
    public List<InterestProduct> findAllByInterestedUser(Long interestedUserId) {
        final User interestedUser = userFindService.findUser(interestedUserId);
        return interestProductRepository.findAllByInterestedUser(interestedUser);
    }

    @Transactional(readOnly = true)
    public Long findInterestProductCountByProduct(Long productId) {
        final Product product = productFindService.findProduct(productId);
        return interestProductRepository.findCountByProduct(product);
    }

    @Transactional
    public Long cancel(Long interestProductId, Long interestedUserId) {
        final InterestProduct interestProduct = findInterestProductById(interestProductId);
        final User user = userFindService.findUser(interestedUserId);
        if (!interestProduct.isRegisteredUser(user)) {
            throw new IllegalArgumentException("user is not registered as a product of interest.");
        }
        interestProductRepository.delete(interestProduct);
        return interestProduct.getId();
    }

    private InterestProduct findInterestProductById(Long interestProductId) {
        return interestProductRepository.findById(interestProductId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found interest product. interestProductId : %d", interestProductId)));
    }
}
