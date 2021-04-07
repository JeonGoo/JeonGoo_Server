package com.toy.jeongoo.user.api.dto.request;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private AddressDto addressDto;

    public SignUpRequest(String email, String password, String name,
                         Gender gender, String phoneNumber, AddressDto addressDto) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.addressDto = addressDto;
    }
}
