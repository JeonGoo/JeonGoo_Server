package com.toy.jeongoo.order.order.api.dto.request;

import com.toy.jeongoo.user.api.dto.AddressDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingInfoRequest {

    private Long ordererId;
    private AddressDto addressDto;

    public ShippingInfoRequest(Long ordererId, AddressDto addressDto) {
        this.ordererId = ordererId;
        this.addressDto = addressDto;
    }
}
