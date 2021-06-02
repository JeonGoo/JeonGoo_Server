package com.toy.jeongoo.payment.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentReadyProductDto {

    private String name;
    private int quantity;
    private long totalAmount;

    public PaymentReadyProductDto(String name, int quantity, long totalAmount) {
        this.name = name;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
}
