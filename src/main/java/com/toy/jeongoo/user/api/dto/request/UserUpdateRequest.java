package com.toy.jeongoo.user.api.dto.request;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private String email;
    private String password;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private AddressDto addressDto;
}
