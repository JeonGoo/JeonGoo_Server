package com.toy.jeongoo.order.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Receiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone_number")
    private String phoneNumber;

    public Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
