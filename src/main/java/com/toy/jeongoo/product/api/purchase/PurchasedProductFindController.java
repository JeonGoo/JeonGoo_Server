package com.toy.jeongoo.product.api.purchase;

import com.toy.jeongoo.product.api.dto.response.ProductShowResponse;
import com.toy.jeongoo.product.model.purchased.PurchasedProduct;
import com.toy.jeongoo.product.service.purchase.PurchasedProductFindService;
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
@RequestMapping("/api/v1/purchased/products")
@RequiredArgsConstructor
public class PurchasedProductFindController {

    private final PurchasedProductFindService purchasedProductFindService;

    @GetMapping
    public DefaultResponse<List<ProductShowResponse>> findAllPurchasedProduct() {
        try {
            final List<PurchasedProduct> purchasedProductList = purchasedProductFindService.findPurchasedProducts();
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowResponses(purchasedProductList));
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/users/{userId}/purchased")
    public DefaultResponse<List<ProductShowResponse>> findAllPurchasedProductByPurchasedUser(@PathVariable Long userId) {
        try {
            final List<PurchasedProduct> purchasedProductList = purchasedProductFindService.findAllPurchasedProductByPurchasedUser(userId);
            final List<ProductShowResponse> productShowResponses = toProductShowResponses(purchasedProductList);
            return DefaultResponse.res(OK, SHOW_PRODUCT, productShowResponses);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @GetMapping("/users/{userId}/sell")
    public DefaultResponse<List<ProductShowResponse>> findAllPurchasedProductBySaleUser(@PathVariable Long userId) {
        try {
            final List<PurchasedProduct> purchasedProductList = purchasedProductFindService.findAllPurchasedProductBySaleUser(userId);
            final List<ProductShowResponse> productShowResponses = toProductShowResponses(purchasedProductList);
            return DefaultResponse.res(OK, SHOW_PRODUCT, productShowResponses);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    private List<ProductShowResponse> toProductShowResponses(List<PurchasedProduct> purchasedProductList) {
        return purchasedProductList.stream()
                .map(p -> new ProductShowResponse(p.getProduct()))
                .collect(Collectors.toList());
    }
}
