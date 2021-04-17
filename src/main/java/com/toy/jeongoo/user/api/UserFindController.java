package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.api.dto.UserDetailDto;
import com.toy.jeongoo.user.model.User;
import com.toy.jeongoo.user.service.UserFindService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserFindController {

    private final UserFindService userFindService;

    @GetMapping("/{userId}")
    public DefaultResponse<UserDetailDto> findUser(@PathVariable Long userId) {
        try {
            final User user = userFindService.findUser(userId);
            return DefaultResponse.res(OK, FIND_USER, toUserDetailDto(user));
        } catch (Exception userException) {
            log.error(userException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, FIND_USER_FAIL);
        }
    }

    private UserDetailDto toUserDetailDto(User user) {
        return new UserDetailDto(user);
    }
}
