package com.toy.jeongoo.product.model.purchase;

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
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "purchase_product_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
