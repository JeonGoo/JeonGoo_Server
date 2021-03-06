package com.toy.jeongoo.user.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {

    @Email(message = "잘못된 형식의 이메일 주소입니다.")
    private String email;

    @NotBlank(message = "로그인시 비밀번호가 공백이면 안됩니다.")
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
