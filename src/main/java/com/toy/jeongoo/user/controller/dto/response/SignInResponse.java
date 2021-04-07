package com.toy.jeongoo.user.controller.dto.response;

import com.toy.jeongoo.user.controller.dto.AddressDto;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SignInResponse {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private AddressDto address;

    public SignInResponse(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
        this.address = new AddressDto(address.getCity(), address.getDetailed());
    }
}
