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

    @Column(name = "orderer_id", nullable = false)
    private Long id;

    @Column(name = "orderer_name", nullable = false)
    private String name;

    @Column(name = "orderer_phone_number", nullable = false)
    private String phoneNumber;

    public Orderer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
