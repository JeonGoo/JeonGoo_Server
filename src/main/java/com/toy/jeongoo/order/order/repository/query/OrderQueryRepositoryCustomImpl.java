package com.toy.jeongoo.order.order.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.order.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.jeongoo.order.order.model.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryCustomImpl implements OrderQueryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findByOrdererId(Long ordererId) {
        return queryFactory.selectFrom(order)
                .where(order.orderer.id.eq(ordererId))
                .fetch();
    }
}
