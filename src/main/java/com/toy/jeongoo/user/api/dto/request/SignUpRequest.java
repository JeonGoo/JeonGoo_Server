package com.toy.jeongoo.user.api.dto.request;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @Email(message = "잘못된 형식의 이메일 주소입니다.")
    private String email;

    @NotBlank(message = "비밀번호가 공백이면 안됩니다.")
    private String password;

    @NotEmpty(message = "이름이 비어있으면 안됩니다.")
    private String name;
    private Gender gender;

    @NotEmpty(message = "핸드폰 번호를 입력해줘야 합니다.")
    private String phoneNumber;

    @NotNull(message = "주소를 입력해줘야 합니다.")
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
