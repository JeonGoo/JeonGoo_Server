package com.toy.jeongoo.order.order.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orderer {

    @Column(name = "orderer_id")
    private Long id;

    @Column(name = "orderer_name")
    private String name;

    @Column(name = "orderer_phone_number")
    private String phoneNumber;

    public Orderer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
