package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductUpdateRequest;
import com.toy.jeongoo.product.service.ProductUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductUpdateController {

    private final ProductUpdateService productUpdateService;

    @PutMapping("/{productId}")
    public DefaultResponse<Long> update(@PathVariable Long productId,
                                        @RequestBody ProductUpdateRequest updateRequest) {
        try {
            final Long product = productUpdateService.update(productId, updateRequest.getProductBasicInfoRequest(), updateRequest.getFileInfoRequest());
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(OK, UPDATE_PRODUCT_FAIL);
        }
    }
}
