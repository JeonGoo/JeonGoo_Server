package com.toy.jeongoo.order.order.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiverRequest {

    private String name;
    private String phoneNumber;

    public ReceiverRequest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
