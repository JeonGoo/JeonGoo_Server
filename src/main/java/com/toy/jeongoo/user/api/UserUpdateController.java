package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.api.dto.request.UserUpdateRequest;
import com.toy.jeongoo.user.service.UserUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserUpdateController {

    private final UserUpdateService userUpdateService;

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 수정 성공"),
            @ApiResponse(code = 400, message = "회원 수정 실패")})
    @PutMapping("{userId}")
    public DefaultResponse<Long> update(@PathVariable Long userId,
                                        @Valid @RequestBody UserUpdateRequest updateRequest) {
        try {
            final Long updatedUserId = userUpdateService.update(userId, updateRequest);
            return DefaultResponse.res(OK, UPDATE_USER, updatedUserId);
        } catch (Exception userException) {
            log.error(userException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_USER_FAIL);
        }
    }
}
