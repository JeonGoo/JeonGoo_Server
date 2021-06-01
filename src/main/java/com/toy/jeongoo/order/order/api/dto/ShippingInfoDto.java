package com.toy.jeongoo.order.order.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.order.model.ShippingInfo;
import com.toy.jeongoo.user.api.dto.AddressDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingInfoDto {

    private AddressDto addressDto;
    private ReceiverDto receiverDto;

    @QueryProjection
    public ShippingInfoDto(ShippingInfo shippingInfo) {
        this.addressDto = new AddressDto(shippingInfo.getAddress());
        this.receiverDto = new ReceiverDto(shippingInfo.getReceiver());
    }
}
