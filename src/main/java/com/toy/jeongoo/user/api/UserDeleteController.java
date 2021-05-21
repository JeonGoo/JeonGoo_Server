package com.toy.jeongoo.user.api;

import com.toy.jeongoo.user.service.UserDeleteService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @ApiOperation(value = "회원 탈퇴", notes = "User의 id와 일치하는 회원을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 탈퇴 성공"),
            @ApiResponse(code = 400, message = "상품 탈퇴 실패")})
    @DeleteMapping("/{userId}")
    public DefaultResponse<Long> delete(@PathVariable Long userId) {
        try {
            final Long deletedUserId = userDeleteService.delete(userId);
            return DefaultResponse.res(OK, DELETE_USER, deletedUserId);
        } catch (Exception userException) {
            log.error(userException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, DELETE_USER_FAIL);
        }
    }
}
