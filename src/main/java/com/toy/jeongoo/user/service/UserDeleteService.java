package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserFindService userFindService;
    private final UserRepository userRepository;

    @Transactional
    public Long delete(Long userId) {
        final User user = userFindService.findUser(userId);
        userRepository.delete(user);
        return user.getId();
    }
}
