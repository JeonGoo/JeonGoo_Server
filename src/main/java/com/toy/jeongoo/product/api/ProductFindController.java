package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.response.ProductShowResponse;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.service.ProductFindService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductFindController {

    private final ProductFindService productFindService;

    @GetMapping("/{productId}")
    public DefaultResponse<ProductShowResponse> showProductDetail(@PathVariable Long productId) {
        try {
            final Product product = productFindService.findProduct(productId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, new ProductShowResponse(product));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping
    public DefaultResponse<List<ProductShowResponse>> findAllProduct() {
        try {
            final List<Product> productList = productFindService.findAllProduct();
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowResponses(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/users/{userId}")
    public DefaultResponse<List<ProductShowResponse>> findAllProductByUser(@PathVariable Long userId) {
        try {
            final List<Product> productList = productFindService.findAllProductByUser(userId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowResponses(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    private List<ProductShowResponse> toProductShowResponses(List<Product> productList) {
        return productList.stream()
                .map(ProductShowResponse::new)
                .collect(Collectors.toList());
    }
}
