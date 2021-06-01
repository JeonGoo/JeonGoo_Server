package com.toy.jeongoo.order.order.api.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.order.api.dto.OrderLineDto;
import com.toy.jeongoo.order.order.api.dto.OrdererDto;
import com.toy.jeongoo.order.order.api.dto.ShippingInfoDto;
import com.toy.jeongoo.order.order.model.OrderLine;
import com.toy.jeongoo.order.order.model.Orderer;
import com.toy.jeongoo.order.order.model.ShippingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class OrderShowResponse {

    private Long orderId;
    private List<OrderLineDto> orderLineDtoList;
    private OrdererDto ordererDto;
    private ShippingInfoDto shippingInfoDto;

    @QueryProjection
    public OrderShowResponse(Long orderId, List<OrderLine> orderLineList, Orderer orderer, ShippingInfo shippingInfo) {
        this.orderId = orderId;
        this.orderLineDtoList = toOrderLineDtoList(orderLineList);
        this.ordererDto = new OrdererDto(orderer);
        this.shippingInfoDto = new ShippingInfoDto(shippingInfo);
    }

    private List<OrderLineDto> toOrderLineDtoList(List<OrderLine> orderLineList) {
        System.out.println(orderLineList.size());
        return orderLineList.stream()
                .map(OrderLineDto::new)
                .collect(Collectors.toList());
    }
}
