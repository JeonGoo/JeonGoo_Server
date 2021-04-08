package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.SignUpRequest;
import com.toy.jeongoo.user.api.dto.response.SignInResponse;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional
    public Long signUp(SignUpRequest signUpRequest) {
        checkDuplicatedEmail(signUpRequest.getEmail());
        final AddressDto addressDto = signUpRequest.getAddressDto();
        final Address address = new Address(addressDto.getCity(), addressDto.getDetailed());
        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .gender(signUpRequest.getGender())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .address(address)
                .build();
        userRepository.save(user);

        return user.getId();
    }

    @Transactional(readOnly = true)
    public SignInResponse signIn(String email, String password) {
        final Optional<User> findUser = userRepository.findByEmailAndPassword(email, password);
        if (!findUser.isPresent()) {
            throw new NoSuchElementException("not found user!");
        }

        return new SignInResponse(findUser.get());
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(String.format("duplicated email! input email: %s", email));
        }
    }
}
