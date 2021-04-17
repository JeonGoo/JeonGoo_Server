package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.api.dto.request.UserUpdateRequest;
import com.toy.jeongoo.user.service.UserUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @PutMapping("{userId}")
    public DefaultResponse<Long> update(@PathVariable Long userId,
                                        @RequestBody UserUpdateRequest updateRequest) {
        try {
            final Long updatedUserId = userUpdateService.update(userId, updateRequest);
            return DefaultResponse.res(OK, UPDATE_USER, updatedUserId);
        } catch (Exception userException) {
            log.error(userException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_USER_FAIL);
        }
    }
}
