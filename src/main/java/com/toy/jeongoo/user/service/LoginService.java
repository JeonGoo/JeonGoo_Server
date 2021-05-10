package com.toy.jeongoo.user.service;

import com.toy.jeongoo.auth.jwt.JwtTokenProvider;
import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.SignInRequest;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.response.SignInResponse;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFindService userFindService;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public Long signUp(SignUpRequest signUpRequest) {
        checkDuplicatedEmail(signUpRequest.getEmail());
        final AddressDto addressDto = signUpRequest.getAddressDto();
        final Address address = new Address(addressDto.getCity(), addressDto.getDetailed());
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .gender(signUpRequest.getGender())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .address(address)
                .build();
        userRepository.save(user);

        return user.getId();
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(SignInRequest signUpRequest) {
        final User user = userFindService.findByEmail(signUpRequest.getEmail());
        checkValidPassword(signUpRequest.getPassword(), user.getPassword());

        return new SignInResponse(user.getId(), tokenProvider.createToken(user.getEmail()));
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(String.format("duplicated email! input email: %s", email));
        }
    }

    private void checkValidPassword(String inputPassword, String originPassword) {
        if (!passwordEncoder.matches(inputPassword, originPassword)) {
            throw new IllegalArgumentException("Password is not matched");
        }
    }

    private User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("incorrect email or password!"));
    }
}
