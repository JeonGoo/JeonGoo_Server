package com.toy.jeongoo.product.repository;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryCustom {
    List<Product> findAllByUserWithInterestProducts(User user);

    List<Product> findAllPageByUserWithInterestProducts(User user, Pageable pageable);

    Optional<Product> findByIdWithUserAndInterestProducts(Long productId);

    List<Product> findAllPageWithUserAndInterestProducts(Pageable pageable);

    List<Product> findAllSaleProductsPage(Pageable pageable);
  
    long deleteAllByUser(User user);
}
