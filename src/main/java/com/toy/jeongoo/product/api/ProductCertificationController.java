package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.service.ProductCertificationService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.ResponseMessage.CERTIFICATE_PRODUCT_FAIL_ERROR;
import static com.toy.jeongoo.utils.StatusCode.BAD_REQUEST;
import static com.toy.jeongoo.utils.StatusCode.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductCertificationController {

    private final ProductCertificationService certificationService;

    @ApiOperation(value = "정품 인증", notes = "정품 인증 상태로 바꾼다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정품 인증 성공"),
            @ApiResponse(code = 400, message = "구매 인증 오류")
    })
    @PutMapping("/{productId}/certification")
    public DefaultResponse<Long> certify(@PathVariable Long productId) {
        try {
            final Long certificationProduct = certificationService.certify(productId);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT, certificationProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_ERROR);
        }
    }

    @ApiOperation(value = "정품 인증 실패", notes = "정품 인증 실패 상태로 바꾼다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "정품 인증 실패 알림 성공"),
            @ApiResponse(code = 400, message = "정품 인증 실패 알림 오류")
    })
    @PutMapping("/{productId}/certification/failure")
    public DefaultResponse<Long> certifyFailed(@PathVariable Long productId,
                                               @ApiParam(name = "정품 인증 실패 이유") @RequestBody ProductCertificationFailedRequest certificationFailedRequest) {
        try {
            Long certificationFailedProduct = certificationService.failed(productId, certificationFailedRequest);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT_FAIL, certificationFailedProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_FAIL_ERROR);
        }
    }
}
