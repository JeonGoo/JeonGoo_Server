package com.toy.jeongoo.user.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressDto {

    private String city;
    private String detailed;

    public AddressDto(String city, String detailed) {
        this.city = city;
        this.detailed = detailed;
    }
}
