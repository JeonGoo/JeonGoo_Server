package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserFindServiceTest {

    @Autowired
    private UserFindService userFindService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void teardown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("id를 통해 회원을 찾는다.")
    void findUser_test() {
        //given
        final Long saveUserId = saveUser();

        //when
        final User user = userFindService.findUser(saveUserId);

        //then
        assertThat(user.getId()).isEqualTo(saveUserId);
    }

    @Test
    @DisplayName("email과 일치하는 회원을 찾는다.")
    void findByEmail_test() {
        //given
        String email = "test@test.com";
        saveUser(email);

        //when
        final User user = userFindService.findByEmail(email);

        //then
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("존재하지 않는 id이면 오류가 발생한다.")
    public void findUser_failTest() throws Exception {
        //given
        final Long saveUserId = saveUser();
        long notFoundUserId = saveUserId + 1;

        //then
        assertThatThrownBy(() -> userFindService.findUser(notFoundUserId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("not found user. input id: %d", notFoundUserId));
    }

    @ParameterizedTest
    @DisplayName("존재하지 않는 email이면 오류가 발생한다.")
    @CsvSource("test@test.com, fail@fail.com")
    public void findByEmail_failTest(String originEmail, String notFoundEmail) throws Exception {
        //given
        saveUser(originEmail);

        //then
        assertThatThrownBy(() -> userFindService.findByEmail(notFoundEmail))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    private Long saveUser() {
        SignUpRequest signUpRequest = new SignUpRequest("test@test.com", "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        return loginService.signUp(signUpRequest);
    }

    private Long saveUser(String email) {
        SignUpRequest signUpRequest = new SignUpRequest(email, "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        return loginService.signUp(signUpRequest);
    }
}