package com.toy.jeongoo.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.jeongoo.product.model.QProduct.*;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByUser(User user) {
        return queryFactory
                .selectFrom(product)
                .where(eqUser(user))
                .fetch();
    }

    private BooleanExpression eqUser(User user) {
        return product.user.eq(user);
    }
}
