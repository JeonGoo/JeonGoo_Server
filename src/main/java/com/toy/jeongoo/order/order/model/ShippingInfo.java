package com.toy.jeongoo.order.order.model;

import com.toy.jeongoo.user.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor
public class ShippingInfo {

    @Embedded
    private Address address;

    @Embedded
    private Orderer orderer;

    public ShippingInfo(Address address, Orderer orderer) {
        this.address = address;
        this.orderer = orderer;
    }
}
