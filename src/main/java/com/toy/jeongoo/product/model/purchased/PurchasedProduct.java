package com.toy.jeongoo.product.model.purchased;

import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PurchasedProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "purchase_product_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User purchasedUser;

    public PurchasedProduct(Product product, User purchasedUser) {
        checkSalesStatus(product);
        this.product = product;
        this.purchasedUser = purchasedUser;
    }

    private void checkSalesStatus(Product product) {
        if (product.isSoldOut()) {
            throw new IllegalArgumentException("This product has already been sold.");
        }
    }
}
