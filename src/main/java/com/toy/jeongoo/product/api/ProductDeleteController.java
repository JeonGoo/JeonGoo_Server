package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.service.ProductDeleteService;
import com.toy.jeongoo.utils.DefaultResponse;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductDeleteController {

    private final ProductDeleteService productDeleteService;

    @DeleteMapping("/{productId}")
    public DefaultResponse<Long> delete(@PathVariable Long productId) {
        try {
            final Long product = productDeleteService.delete(productId);
            return DefaultResponse.res(OK, DELETE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, DELETE_PRODUCT_FAIL);
        }
    }
}
