package com.toy.jeongoo.product.repository;

import com.toy.jeongoo.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
