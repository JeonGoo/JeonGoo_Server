package com.toy.jeongoo.order.product.model;

import com.toy.jeongoo.common.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Column(name = "order_product_id", nullable = false)
    private Long id;

    @Column(name = "order_product_name")
    private String name;

    @AttributeOverride(name = "value", column = @Column(name = "order_product_price", nullable = false))
    private Money price;

    public OrderProduct(Long id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Money calculateAmount(int quantity) {
        return this.price.mul(quantity);
    }
}
