package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.response.SignInResponse;
import com.toy.jeongoo.user.service.LoginService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserLoginController {

    private final LoginService loginService;
    @ApiOperation(value = "회원 가입", notes = "회원 가입을 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 가입 성공"),
            @ApiResponse(code = 400, message = "상품 가입 실패")})
    @PostMapping("/signup")
    public DefaultResponse<Long> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            final Long signUpNumber = loginService.signUp(signUpRequest);
            return DefaultResponse.res(CREATED, CREATE_USER, signUpNumber);
        } catch (Exception loginException) {
            log.error(loginException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CREATE_USER_FAIL);
        }
    }

    @ApiOperation(value = "로그인", notes = "Email과 Password가 일치하면 토큰 및 id를 반환한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "로그인 실패")})
    @PostMapping("/signin")
    public DefaultResponse<SignInResponse> signIn(@Valid @RequestBody SignInRequest signUpRequest) {
        try {
            final SignInResponse signInResponse = loginService.signIn(signUpRequest);
            return DefaultResponse.res(OK, LOGIN_USER, signInResponse);
        } catch (Exception loginException) {
            log.error(loginException.getMessage());
            return DefaultResponse.res(UNAUTHORIZED, LOGIN_USER_FAIL);
        }
    }
}
