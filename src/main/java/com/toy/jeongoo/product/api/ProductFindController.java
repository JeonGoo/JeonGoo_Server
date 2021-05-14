package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductShowDetailWIthInterestedStatusResponse;
import com.toy.jeongoo.product.api.dto.response.ProductShowDetailResponse;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.service.ProductFindService;
import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductFindController {

    private final ProductFindService productFindService;
    private final InterestProductService interestProductService;

    @GetMapping("/{productId}")
    public DefaultResponse<ProductShowDetailResponse> showProductDetail(@PathVariable Long productId) {
        try {
            final Product product = productFindService.showProductDetail(productId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, new ProductShowDetailResponse(product));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/{productId}/users/{interestedUserId}")
    public DefaultResponse<ProductShowDetailWIthInterestedStatusResponse> showProductDetailWithInterestedStatus(@PathVariable Long productId,
                                                                                                                @PathVariable Long interestedUserId) {
        try {
            final Product product = productFindService.showProductDetail(productId);
            final Optional<InterestProduct> interestProduct = interestProductService.findByProductAndInterestedUser(productId, interestedUserId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, new ProductShowDetailWIthInterestedStatusResponse(product, interestProduct.isPresent()));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping
    public DefaultResponse<List<ProductShowDetailResponse>> showAllProductsDetail() {
        try {
            final List<Product> productList = productFindService.findAllProduct();
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowDetailResponseList(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/users/{userId}")
    public DefaultResponse<List<ProductShowDetailResponse>> findAllProductByUser(@PathVariable Long userId) {
        try {
            final List<Product> productList = productFindService.findAllProductByUser(userId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowDetailResponseList(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/sale")
    public DefaultResponse<List<ProductShowDetailResponse>> showAllSaleProducts() {
        try {
            final List<Product> productList = productFindService.showAllSaleProducts();
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowDetailResponseList(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    private List<ProductShowDetailResponse> toProductShowDetailResponseList(List<Product> productList) {
        return productList.stream()
                .map(ProductShowDetailResponse::new)
                .collect(Collectors.toList());
    }
}
