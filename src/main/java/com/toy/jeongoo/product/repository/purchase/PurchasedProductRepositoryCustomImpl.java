package com.toy.jeongoo.product.repository.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.jeongoo.product.model.QProduct.product;
import static com.toy.jeongoo.product.model.purchased.QPurchasedProduct.*;

@Repository
@RequiredArgsConstructor
public class PurchasedProductRepositoryCustomImpl implements PurchasedProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PurchasedProduct> findAllByPurchasedUserWithProductAndPurchasedUser() {
        final List<PurchasedProduct> purchasedProductList = queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .innerJoin(purchasedProduct.purchasedUser).fetchJoin()
                .fetch();

        queryFactory.select(product)
                .from(product, purchasedProduct)
                .innerJoin(product.user).fetchJoin()
                .where(purchasedProduct.in(purchasedProductList))
                .fetch();

        return purchasedProductList;
    }

    @Override
    public List<PurchasedProduct> findAllByPurchasedUserWithProduct(User purchasedUser) {
        final List<PurchasedProduct> purchasedProductList = queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .innerJoin(purchasedProduct.purchasedUser)
                .where(purchasedProduct.purchasedUser.eq(purchasedUser))
                .fetch();

        queryFactory.select(product)
                .from(product, purchasedProduct)
                .innerJoin(product.user).fetchJoin()
                .where(purchasedProduct.in(purchasedProductList))
                .fetch();

        return purchasedProductList;
    }

    @Override
    public List<PurchasedProduct> findAllBySaleUserWithProduct(User saleUser) {
        final List<PurchasedProduct> purchasedProductList = queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .innerJoin(purchasedProduct.purchasedUser)
                .where(purchasedProduct.product.user.eq(saleUser))
                .fetch();

        queryFactory.select(product)
                .from(product, purchasedProduct)
                .innerJoin(product.user).fetchJoin()
                .where(purchasedProduct.in(purchasedProductList))
                .fetch();

        return purchasedProductList;
    }
}
