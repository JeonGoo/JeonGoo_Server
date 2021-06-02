package com.toy.jeongoo.order.order.repository.query;

import com.toy.jeongoo.order.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQueryRepository extends JpaRepository<Order, Long>, OrderQueryRepositoryCustom {
}
