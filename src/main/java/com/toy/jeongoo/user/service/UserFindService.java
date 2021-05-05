package com.toy.jeongoo.user.service;

import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFindService {

    private final UserRepository userRepository;

    public User findUser(Long userId) {
        return findUserById(userId);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(String.format("not found user. input id: %d", userId)));
    }
}
