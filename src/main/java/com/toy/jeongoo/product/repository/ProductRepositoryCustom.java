package com.toy.jeongoo.product.repository;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.model.User;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryCustom {
    List<Product> findAllByUserWithInterestProducts(User user);

    Optional<Product> findByIdWithUserAndInterestProducts(Long productId);

    List<Product> findAllWithUserAndInterestProducts();

    List<Product> findAllSaleProducts();
}
