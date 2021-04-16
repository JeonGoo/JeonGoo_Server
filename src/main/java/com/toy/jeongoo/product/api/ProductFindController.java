package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.response.ProductFindResponse;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.service.ProductFindService;
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
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductFindController {

    private final ProductFindService productFindService;

    @GetMapping("/{productId}")
    public DefaultResponse<ProductFindResponse> findProduct(@PathVariable Long productId) {
        try {
            final Product product = productFindService.findProduct(productId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, new ProductFindResponse(product));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }
}
