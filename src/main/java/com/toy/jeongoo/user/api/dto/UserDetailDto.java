package com.toy.jeongoo.user.api.dto;

import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private AddressDto addressDto;

    public UserDetailDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
        this.addressDto = toAddressDto(user.getAddress());
    }

    private AddressDto toAddressDto(Address address) {
        return new AddressDto(address.getCity(), address.getDetailed());
    }
}
