package com.toy.jeongoo.payment.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentReadyRequest {

    private String cid;
    private String orderId;
    private String userId;
    private String taxFreeAmount;
    private PaymentReadyProductDto productDto;

    public PaymentReadyRequest(String cid, String orderId, String userId, String taxFreeAmount, PaymentReadyProductDto productDto) {
        this.cid = cid;
        this.orderId = orderId;
        this.userId = userId;
        this.taxFreeAmount = taxFreeAmount;
        this.productDto = productDto;
    }
}
