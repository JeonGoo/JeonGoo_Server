package com.toy.jeongoo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }
}
