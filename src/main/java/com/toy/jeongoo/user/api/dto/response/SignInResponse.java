package com.toy.jeongoo.user.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponse {

    private Long id;
    private String token;

    public SignInResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
