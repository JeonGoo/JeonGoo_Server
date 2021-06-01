package com.toy.jeongoo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Money {

    private Long value;

    public Money(Long value) {
        checkNegativeValue(value);
        this.value = value;
    }

    public static Money of(Long value) {
        return new Money(value);
    }

    private void checkNegativeValue(Long value) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("money should not be negative. input money: %d", value));
        }
    }

    public Money mul(int quantity) {
        return Money.of(this.value * quantity);
    }
}
