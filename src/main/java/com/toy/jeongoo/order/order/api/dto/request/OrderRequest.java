package com.toy.jeongoo.order.order.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderRequest {

    private List<OrderLineRequest> orderLineRequestList;
    private Long ordererId;
    private ShippingInfoRequest shippingInfoRequest;

    public OrderRequest(List<OrderLineRequest> orderLineRequestList, Long ordererId, ShippingInfoRequest shippingInfoRequest) {
        this.orderLineRequestList = orderLineRequestList;
        this.ordererId = ordererId;
        this.shippingInfoRequest = shippingInfoRequest;
    }
}
