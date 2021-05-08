package com.toy.jeongoo.product.api.interest;

import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.utils.DefaultResponse;
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
@RequestMapping("/api/v1/interest/products")
@RequiredArgsConstructor
public class InterestProductController {

    private final InterestProductService interestProductService;

    @PostMapping("/{productId}/users/{userId}")
    public DefaultResponse<Long> registerInterestProduct(@PathVariable Long productId,
                                                         @PathVariable Long userId) {
        try {
            Long interestProductId = interestProductService.registerInterestProduct(productId, userId);
            return DefaultResponse.res(OK, REGISTER_INTEREST_PRODUCT, interestProductId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, REGISTER_INTEREST_PRODUCT_FAIL);
        }
    }
}
