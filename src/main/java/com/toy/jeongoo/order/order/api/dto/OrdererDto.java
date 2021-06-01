package com.toy.jeongoo.order.order.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.jeongoo.order.order.model.Orderer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdererDto {

    private long id;
    private String name;
    private String phoneNumber;

    @QueryProjection
    public OrdererDto(Orderer orderer) {
        this.id = orderer.getId();
        this.name = orderer.getName();
        this.phoneNumber = orderer.getPhoneNumber();
    }
}
