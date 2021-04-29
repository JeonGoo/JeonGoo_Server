package com.toy.jeongoo.product.repository.purchase;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.jeongoo.product.model.purchased.QPurchasedProduct.*;

@Repository
@RequiredArgsConstructor
public class PurchasedProductRepositoryCustomImpl implements PurchasedProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PurchasedProduct> findAllWithProductAndPurchasedUser() {
        return queryFactory
                .selectFrom(purchasedProduct)
                .innerJoin(purchasedProduct.product).fetchJoin()
                .innerJoin(purchasedProduct.purchasedUser).fetchJoin()
                .fetch();
    }
}
