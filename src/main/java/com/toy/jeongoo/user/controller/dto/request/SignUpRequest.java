package com.toy.jeongoo.user.controller.dto.request;

import com.toy.jeongoo.user.controller.dto.AddressDto;
import com.toy.jeongoo.user.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private AddressDto addressDto;
    private String PhoneNumber;

    public SignUpRequest(String email, String password, String name,
                         Gender gender, AddressDto addressDto, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.addressDto = addressDto;
        PhoneNumber = phoneNumber;
    }
}
