package com.toy.jeongoo.user.api.dto;

import com.toy.jeongoo.user.model.Address;
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

    public AddressDto(Address address) {
        this.city = address.getCity();
        this.detailed = address.getDetailed();
    }
}
