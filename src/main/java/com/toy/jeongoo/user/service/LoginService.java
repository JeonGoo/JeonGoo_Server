package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.controller.dto.AddressDto;
import com.toy.jeongoo.user.controller.dto.request.SignUpRequest;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private void checkDuplicatedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(String.format("duplicated email! input email: %s", email));
        }
    }
}
