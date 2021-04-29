package com.toy.jeongoo.user.api.dto.response;

import com.toy.jeongoo.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserShowResponse {

    private String name;
    private String phoneNumber;

    public UserShowResponse(User user) {
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
    }
}
