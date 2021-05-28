package com.toy.jeongoo.order.product.model;

import com.toy.jeongoo.common.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    private Long productId;
    private String name;
    private Money price;

    public OrderProduct(Long productId, String name, Money price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
