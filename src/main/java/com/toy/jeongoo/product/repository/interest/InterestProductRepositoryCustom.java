package com.toy.jeongoo.product.repository.interest;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.user.model.User;

import java.util.List;

public interface InterestProductRepositoryCustom {
    List<InterestProduct> findAllByInterestedUser(User interestedUser);

    Long findCountByProduct(Product product);

    long deleteAllByInterestedUser(User user);
}
