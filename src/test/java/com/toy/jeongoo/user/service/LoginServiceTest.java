package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.response.SignInResponse;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void teardown() {
        userRepository.deleteAll();
    }

    @DisplayName("회원가입 한다.")
    @Test
    public void signInTest() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("test@tset.com", "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));

        //when
        final Long userId = loginService.signUp(signUpRequest);

        //then
        assertThat(userId).isEqualTo(1L);
    }

    @DisplayName("email과 password가 일치하면 로그인이 성공한다.")
    @ParameterizedTest
    @CsvSource({"test@test.com, 1234", "user@user.com, 4567"})
    public void signUpTest(String email, String password) throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest(email, password, "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        loginService.signUp(signUpRequest);

        SignInRequest signInRequest = new SignInRequest(email, password);

        //when
        final SignInResponse signInResponse = loginService.signIn(signInRequest);
        final User user = userRepository.findById(signInResponse.getId()).get();

        //then
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @DisplayName("email과 password가 일치하지 않으면 로그인이 실패한다.")
    @ParameterizedTest
    @CsvSource({"test@test.com, 1234, fail@fail.com, 1234", "test@test.com, 1234, test@test.com, 4567",
            "user@user.com, 4567, fail@fail.com, 4567", "user@user.com, 4567, user@user.com, 1234"})
    public void signUp_failTest(String originEmail, String originPassword, String inputEmail, String inputPassword) throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest(originEmail, originPassword, "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        loginService.signUp(signUpRequest);

        SignInRequest signInRequest = new SignInRequest(inputEmail, inputPassword);

        //then
        assertThatThrownBy(() -> loginService.signIn(signInRequest));
    }
}