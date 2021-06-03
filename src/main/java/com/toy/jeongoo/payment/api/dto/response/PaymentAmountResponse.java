package com.toy.jeongoo.payment.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentAmountResponse {

    private int total;
    private int tax_free;
    private int vat;
    private int point;
    private int discount;

    public PaymentAmountResponse(int total, int tax_free, int vat, int point, int discount) {
        this.total = total;
        this.tax_free = tax_free;
        this.vat = vat;
        this.point = point;
        this.discount = discount;
    }
}
