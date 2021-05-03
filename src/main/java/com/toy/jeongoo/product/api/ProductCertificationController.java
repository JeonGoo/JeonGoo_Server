package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductCertificationFailedRequest;
import com.toy.jeongoo.product.api.dto.request.ProductCertificationRequest;
import com.toy.jeongoo.product.service.ProductCertificationService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping("/{productId}/certification")
    public DefaultResponse<Long> certify(@PathVariable Long productId,
                                         @Valid @RequestBody ProductCertificationRequest certificationRequest) {
        try {
            final Long certificationProduct = certificationService.certify(productId, certificationRequest);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT, certificationProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_ERROR);
        }
    }

    @PutMapping("/{productId}/certification/failure")
    public DefaultResponse<Long> certifyFailed(@PathVariable Long productId,
                                               @RequestBody ProductCertificationFailedRequest certificationFailedRequest) {
        try {
            Long certificationFailedProduct = certificationService.failed(productId, certificationFailedRequest);
            return DefaultResponse.res(OK, CERTIFICATE_PRODUCT_FAIL, certificationFailedProduct);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CERTIFICATE_PRODUCT_FAIL_ERROR);
        }
    }
}
