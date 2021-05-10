package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.request.UserUpdateRequest;
import com.toy.jeongoo.user.model.Gender;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UserUpdateServiceTest {

    @Autowired
    private UserUpdateService userUpdateService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserFindService userFindService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void teardown() {
        userRepository.deleteAll();
    }

    @ParameterizedTest
    @DisplayName("수정된 회원정보를 업데이트한다.")
    @CsvSource("origin@origin.com, new@new.com")
    public void update_test(String originEmail, String newEmail) throws Exception {
        //given
        final Long originUserId = saveUser(originEmail);
        final UserUpdateRequest updateRequest = new UserUpdateRequest(newEmail, "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        //when
        userUpdateService.update(originUserId, updateRequest);
        final User user = userFindService.findUser(originUserId);

        //then
        assertThat(user.getEmail()).isEqualTo(newEmail);
    }

    @ParameterizedTest
    @DisplayName("이미 존재하는 email이면 오류가 발생한다.")
    @CsvSource("origin@origin.com, someone@someone.com")
    public void update_failTest(String originEmail, String someoneEmail) throws Exception {
        //given
        final Long originUserId = saveUser(originEmail);
        saveUser(someoneEmail);

        final UserUpdateRequest updateRequest = new UserUpdateRequest(someoneEmail, "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));

        //then
        assertThatThrownBy(() -> userUpdateService.update(originUserId, updateRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("This email already exists. input email: %s", someoneEmail));
    }

    private Long saveUser(String email) {
        SignUpRequest signUpRequest = new SignUpRequest(email, "1234", "bang",
                Gender.MALE, "1234", new AddressDto("중구", "기현동"));
        return loginService.signUp(signUpRequest);
    }
}
