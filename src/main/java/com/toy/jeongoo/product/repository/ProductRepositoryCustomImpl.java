package com.toy.jeongoo.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.status.SalesStatus;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public List<Product> findAllPageByUserWithInterestProducts(User user, Pageable pageable) {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .leftJoin(product.interestProductList).fetchJoin()
                .where(eqUser(user))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
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
    public List<Product> findAllPageWithUserAndInterestProducts(Pageable pageable) {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .innerJoin(product.user).fetchJoin()
                .leftJoin(product.interestProductList).fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    @Override
    public long deleteAllByUser(User user) {
        return queryFactory.delete(product)
                .where(product.user.eq(user))
                .execute();
    }

    @Override
    public List<Product> findAllSaleProductsPage(Pageable pageable) {
        return queryFactory
                .selectFrom(product)
                .distinct()
                .innerJoin(product.user).fetchJoin()
                .leftJoin(product.interestProductList).fetchJoin()
                .where(isSale())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
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
