package com.toy.jeongoo.product.repository.interest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.toy.jeongoo.product.model.QProduct.product;
import static com.toy.jeongoo.product.model.interest.QInterestProduct.interestProduct;

@Repository
@RequiredArgsConstructor
public class InterestProductRepositoryCustomImpl implements InterestProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<InterestProduct> findAllByInterestedUser(User interestedUser) {
        queryFactory.select(product)
                .from(product, interestProduct)
                .join(product.user).fetchJoin()
                .where(interestProduct.interestedUser.eq(interestedUser))
                .fetch();

        return queryFactory.selectFrom(interestProduct)
                .where(interestProduct.interestedUser.eq(interestedUser))
                .fetch();
    }

    @Override
    public Long findCountByProduct(Product product) {
        return queryFactory
                .selectFrom(interestProduct)
                .where(interestProduct.product.eq(product))
                .fetchCount();
    }

    @Override
    public Optional<InterestProduct> findByProductAndInterestedUser(Product product, User interestedUser) {
        return Optional.ofNullable(queryFactory
                .selectFrom(interestProduct)
                .where(interestProduct.product.eq(product)
                        .and(interestProduct.interestedUser.eq(interestedUser)))
                .fetchOne());
    }
}
