package com.toy.jeongoo.user.controller;

import com.toy.jeongoo.user.controller.dto.request.SignUpRequest;
import com.toy.jeongoo.user.service.LoginService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController("users/v1/")
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public DefaultResponse<Long> signUp(SignUpRequest signUpRequest) {
        try {
            final Long signUpNumber = loginService.signUp(signUpRequest);
            return DefaultResponse.res(CREATED, CREATE_USER, signUpNumber);
        } catch (Exception signUpException) {
            log.error(signUpException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CREATE_USER_FAIL);
        }
    }
}
