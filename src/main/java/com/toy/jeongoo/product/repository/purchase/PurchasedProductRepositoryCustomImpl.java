package com.toy.jeongoo.product.repository.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.jeongoo.product.model.purchased.QPurchasedProduct.*;

@Repository
@RequiredArgsConstructor
public class PurchasedProductRepositoryCustomImpl implements PurchasedProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PurchasedProduct> findAllByPurchasedUserWithProductAndPurchasedUser() {
        return queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .innerJoin(purchasedProduct.purchasedUser).fetchJoin()
                .fetch();
    }

    @Override
    public List<PurchasedProduct> findAllByPurchasedUserWithProduct(User purchasedUser) {
        return queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .where(purchasedProduct.purchasedUser.eq(purchasedUser))
                .fetch();
    }

    @Override
    public List<PurchasedProduct> findAllBySaleUserWithProduct(User saleUser) {
        return queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .where(purchasedProduct.product.user.eq(saleUser))
                .fetch();
    }
}
