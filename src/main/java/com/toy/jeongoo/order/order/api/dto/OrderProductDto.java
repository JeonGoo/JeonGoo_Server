package com.toy.jeongoo.order.order.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.product.model.OrderProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductDto {

    private long id;
    private String name;
    private long price;

    @QueryProjection
    public OrderProductDto(OrderProduct orderProduct) {
        this.id = orderProduct.getId();
        this.name = orderProduct.getName();
        this.price = orderProduct.getPrice().getValue();
    }
}
