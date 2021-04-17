package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.api.dto.AddressDto;
import com.toy.jeongoo.user.api.dto.request.UserUpdateRequest;
import com.toy.jeongoo.user.model.Address;
import com.toy.jeongoo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserFindService userFindService;

    @Transactional
    public Long update(Long userId, UserUpdateRequest updateRequest) {
        final User user = userFindService.findUser(userId);
        user.update(updateRequest.getEmail(), updateRequest.getPassword(), updateRequest.getName(),
                updateRequest.getPhoneNumber(), updateRequest.getGender(), toAddress(updateRequest.getAddressDto()));
        return user.getId();
    }

    private Address toAddress(AddressDto addressDto) {
        return new Address(addressDto.getCity(), addressDto.getDetailed());
    }
}
