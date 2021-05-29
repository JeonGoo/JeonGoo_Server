package com.toy.jeongoo.order.order.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderLineRequest {

    private Long productId;
    private int quantity;

    public OrderLineRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
