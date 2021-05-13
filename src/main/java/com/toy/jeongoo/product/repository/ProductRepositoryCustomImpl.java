package com.toy.jeongoo.product.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.SalesStatus;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.toy.jeongoo.product.model.QProduct.*;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findAllByUserWithInterestProducts(User user) {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .leftJoin(product.interestProductList).fetchJoin()
                .where(eqUser(user))
                .fetch();
    }

    @Override
    public Optional<Product> findByIdWithUserAndInterestProducts(Long productId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(product)
                .distinct()
                .innerJoin(product.user).fetchJoin()
                .leftJoin(product.interestProductList).fetchJoin()
                .where(eqProductId(productId))
                .fetchOne());
    }

    @Override
    public List<Product> findAllWithUserAndInterestProducts() {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .innerJoin(product.user).fetchJoin()
                .leftJoin(product.interestProductList).fetchJoin()
                .fetch();
    }

    @Override
    public List<Product> findAllSaleProducts() {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .innerJoin(product.user).fetchJoin()
                .leftJoin(product.interestProductList).fetchJoin()
                .where(isSale())
                .fetch();
    }

    private BooleanExpression isSale() {
        return product.salesStatus.eq(SalesStatus.SALE);
    }

    private BooleanExpression eqProductId(Long productId) {
        return product.id.eq(productId);
    }

    private BooleanExpression eqUser(User user) {
        return product.user.eq(user);
    }
}
