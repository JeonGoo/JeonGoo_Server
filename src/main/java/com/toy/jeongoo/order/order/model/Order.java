package com.toy.jeongoo.order.order.model;

import com.toy.jeongoo.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Table(name = "ORDERS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLineList = new ArrayList<>();

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfo shippingInfo;

    public Order(List<OrderLine> orderLineList, Orderer orderer, ShippingInfo shippingInfo) {
        this.orderLineList = orderLineList;
        this.orderer = orderer;
        this.shippingInfo = shippingInfo;
        checkOneOrMoreOrderLine(orderLineList);
        for (OrderLine orderLine : orderLineList) {
            orderLine.changeOrder(this);
        }
    }

    private void checkOneOrMoreOrderLine(List<OrderLine> orderLineList) {
        if (orderLineList.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one order list.");
        }
    }
}
