package com.toy.jeongoo.product.api.interest;

import com.toy.jeongoo.product.api.dto.response.ProductShowResponse;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/users/{interestedUserId}")
    public DefaultResponse<List<ProductShowResponse>> findAllInterestProductByInterestedUser(@PathVariable Long interestedUserId) {
        try {
            List<InterestProduct> interestProductList = interestProductService.findAllByInterestedUser(interestedUserId);
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowResponses(interestProductList));
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    private List<ProductShowResponse> toProductShowResponses(List<InterestProduct> interestProductList) {
        return interestProductList.stream()
                .map(p -> new ProductShowResponse(p.getProduct()))
                .collect(Collectors.toList());
    }
}
