package com.toy.jeongoo.order.order.model;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.order.product.model.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @Embedded
    private Product product;

    private int quantity;
    private Money totalAmount;

    public OrderLine(Product product, int quantity, Money totalAmount) {
        this.product = product;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
}
