package com.toy.jeongoo.order.order.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.order.model.Receiver;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceiverDto {

    private String name;
    private String phoneNumber;

    @QueryProjection
    public ReceiverDto(Receiver receiver) {
        this.name = receiver.getName();
        this.phoneNumber = receiver.getPhoneNumber();
    }
}
