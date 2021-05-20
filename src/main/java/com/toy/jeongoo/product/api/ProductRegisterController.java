package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductRegistrationRequest;
import com.toy.jeongoo.product.service.ProductRegistrationService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRegisterController {

    private final ProductRegistrationService registrationService;

    @ApiOperation(value = "상품 등록", notes = "상품을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "상품 등록 성공"),
            @ApiResponse(code = 400, message = "상품 등록 실패")
    })
    @PostMapping("/users/{userId}")
    public DefaultResponse<Long> register(@ApiParam(name = "상품에 대한 정보", required = true) @RequestBody ProductRegistrationRequest productRequest,
                                          @PathVariable Long userId) {
        try {
            final Long registrationId = registrationService.register(productRequest.getProductBasicInfoRequest(), productRequest.getFileInfoRequest(), userId);
            return DefaultResponse.res(CREATED, REGISTER_PRODUCT, registrationId);
        } catch (Exception productRegistrationException) {
            log.error(productRegistrationException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, REGISTER_PRODUCT_FAIL);
        }
    }
}
