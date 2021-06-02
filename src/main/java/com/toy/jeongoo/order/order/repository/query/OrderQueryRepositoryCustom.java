package com.toy.jeongoo.order.order.repository.query;

import com.toy.jeongoo.order.order.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQueryRepositoryCustom {
    List<Order> findByOrdererId(Long ordererId);
}
