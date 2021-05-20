package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.service.ProductPurchaseService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductPurchaseController {

    private final ProductPurchaseService productPurchaseService;

    @ApiOperation(value = "상품 구매", notes = "상품을 구매한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 구매 성공"),
            @ApiResponse(code = 400, message = "상품 구매 실패")
    })
    @PostMapping("/{productId}/purchase/{userId}")
    public DefaultResponse<Long> purchase(@PathVariable Long productId,
                                          @PathVariable Long userId) {
        try {
            Long purchaseProduct = productPurchaseService.purchase(productId, userId);
            return DefaultResponse.res(OK, PRODUCT_PURCHASE, purchaseProduct);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, PRODUCT_PURCHASE_FAIL);
        }
    }
}
