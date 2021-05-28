package com.toy.jeongoo.order.order.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orderer {

    private Long ordererId;
    private String name;
    private String phoneNumber;

    public Orderer(Long ordererId, String name, String phoneNumber) {
        this.ordererId = ordererId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
