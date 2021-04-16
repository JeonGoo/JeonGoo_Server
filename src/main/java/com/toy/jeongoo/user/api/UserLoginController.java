package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.service.LoginService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public DefaultResponse<Long> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            final Long signUpNumber = loginService.signUp(signUpRequest);
            return DefaultResponse.res(CREATED, CREATE_USER, signUpNumber);
        } catch (Exception loginException) {
            log.error(loginException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CREATE_USER_FAIL);
        }
    }

    @PostMapping("/signin")
    public DefaultResponse<Long> signIn(@RequestBody SignInRequest signUpRequest) {
        try {
            final Long user = loginService.signIn(signUpRequest.getEmail(), signUpRequest.getPassword());
            return DefaultResponse.res(OK, LOGIN_USER, user);
        } catch (Exception loginException) {
            log.error(loginException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, LOGIN_USER_FAIL);
        }
    }
}
