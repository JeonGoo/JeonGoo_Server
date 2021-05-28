package com.toy.jeongoo.order.order.model;

import com.toy.jeongoo.common.Money;
import com.toy.jeongoo.common.entity.BaseTimeEntity;
import com.toy.jeongoo.order.product.model.OrderProduct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

    @Embedded
    private OrderProduct orderProduct;

    private int quantity;
    private Money totalAmount;


    public OrderLine(OrderProduct orderProduct, int quantity) {
        this.orderProduct = orderProduct;
        this.quantity = quantity;
    }
}
