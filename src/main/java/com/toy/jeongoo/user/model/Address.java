package com.toy.jeongoo.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Address {

    @Column(name = "city")
    private String city;

    @Column(name = "detailed")
    private String detailed;

    public Address(String city, String detailed) {
        this.city = city;
        this.detailed = detailed;
    }
}