package com.toy.jeongoo.payment.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PaymentApproveResponse {

    private String aid, tid, cid, sid;
    private String partner_order_id, partner_user_id, payment_method_type;
    private PaymentAmountResponse amount;
    private PaymentCardInfoResponse card_info;
    private String item_name, item_code, payload;
    private Integer quantity, tax_free_amount, vat_amount;
    private Date created_at, approved_at;
}

