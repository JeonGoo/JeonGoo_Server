package com.toy.jeongoo.user.api;

import com.toy.jeongoo.auth.jwt.JwtTokenProvider;
import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.response.SignInResponse;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.LoginService;
import com.toy.jeongoo.user.service.UserFindService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserFindService userFindService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

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
    public DefaultResponse<SignInResponse> signIn(@RequestBody SignInRequest signUpRequest) {
        try {
            final User user = userFindService.findByEmail(signUpRequest.getEmail());
            if (!passwordEncoder.matches(signUpRequest.getPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Password is not matched");
            }
            final String token = tokenProvider.createToken(user.getEmail());
            return DefaultResponse.res(OK, LOGIN_USER, new SignInResponse(user.getId(), token));
        } catch (Exception loginException) {
            log.error(loginException.getMessage());
            return DefaultResponse.res(UNAUTHORIZED, LOGIN_USER_FAIL);
        }
    }
}
