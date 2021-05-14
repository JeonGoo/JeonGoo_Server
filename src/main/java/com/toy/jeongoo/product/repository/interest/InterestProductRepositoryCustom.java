package com.toy.jeongoo.product.repository.interest;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.user.model.User;

import java.util.List;
import java.util.Optional;

public interface InterestProductRepositoryCustom {
    List<InterestProduct> findAllByInterestedUser(User interestedUser);

    Long findCountByProduct(Product product);

    Optional<InterestProduct> findByProductAndInterestedUser(Product product, User interestedUser);
}
