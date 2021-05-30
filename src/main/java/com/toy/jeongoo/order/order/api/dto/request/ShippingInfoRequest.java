package com.toy.jeongoo.order.order.api.dto.request;

import com.toy.jeongoo.user.api.dto.AddressDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingInfoRequest {

    private ReceiverRequest receiverRequest;
    private AddressDto addressDto;

    public ShippingInfoRequest(ReceiverRequest receiverRequest, AddressDto addressDto) {
        this.receiverRequest = receiverRequest;
        this.addressDto = addressDto;
    }
}
