package com.toy.jeongoo.product.model.interest;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class InterestProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "interest_product_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User interestedUser;

    public static InterestProduct createInterestProduct(Product product, User interestedUser) {
        final InterestProduct interestProduct = new InterestProduct(product, interestedUser);
        product.addInterestProduct(interestProduct);
        return interestProduct;
    }

    public InterestProduct(Product product, User interestedUser) {
        this.product = product;
        this.interestedUser = interestedUser;
    }

    public boolean isRegisteredUser(User user) {
        return this.interestedUser.equals(user);
    }
}
