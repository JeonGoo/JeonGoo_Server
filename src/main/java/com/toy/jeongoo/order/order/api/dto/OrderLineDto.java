package com.toy.jeongoo.order.order.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.order.model.OrderLine;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderLineDto {

    private Long id;
    private OrderProductDto orderProductDto;
    private int quantity;
    private long totalAmount;

    @QueryProjection
    public OrderLineDto(OrderLine orderLine) {
        this.id = orderLine.getId();
        this.orderProductDto = new OrderProductDto(orderLine.getOrderProduct());
        this.quantity = orderLine.getQuantity();
        this.totalAmount = orderLine.getTotalAmount().getValue();
    }
}
