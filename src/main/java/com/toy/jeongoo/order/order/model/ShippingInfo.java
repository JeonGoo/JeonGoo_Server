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
    private Receiver receiver;

    public ShippingInfo(Address address, Receiver receiver) {
        this.address = address;
        this.receiver = receiver;
    }
}
