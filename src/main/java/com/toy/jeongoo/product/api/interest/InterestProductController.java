package com.toy.jeongoo.product.api.interest;

import com.toy.jeongoo.product.api.dto.response.ProductShowResponse;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "관심 상품 등록", notes = "Product의 Id와 User의 Id를 통해 관심 상품을 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "관심 상품 등록 성공"),
            @ApiResponse(code = 400, message = "관심 상품 등록 실패")
    })
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

    @ApiOperation(value = "회원이 관심 상품으로 등록한 목록 조회", notes = "User의 Id와 연관되어 있는 관심 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 상품 등록 성공"),
            @ApiResponse(code = 400, message = "관심 상품 등록 실패")
    })
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

    @ApiOperation(value = "관심 상품 삭제", notes = "User Id가 등록한 InterestProduct의 Id가 맞다면 해당 관심 상품을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 상품 등록 성공"),
            @ApiResponse(code = 400, message = "관심 상품 등록 실패")
    })
    @DeleteMapping("/{interestProductId}/users/{interestedUserId}")
    public DefaultResponse<Long> cancelInterestProduct(@PathVariable Long interestProductId,
                                                       @PathVariable Long interestedUserId) {
        try {
            Long cancelledInterestProductId = interestProductService.cancel(interestProductId, interestedUserId);
            return DefaultResponse.res(OK, CANCEL_INTEREST_PRODUCT, cancelledInterestProductId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CANCEL_INTEREST_PRODUCT_FAIL);
        }
    }

    @ApiOperation(value = "관심 상품 삭제", notes = "등록되어있는 Product의 Id와 User의 Id로 관심 상품을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 상품 등록 성공"),
            @ApiResponse(code = 400, message = "관심 상품 등록 실패")
    })
    @DeleteMapping("/registered/products/{productId}/users/{interestedUserId}")
    public DefaultResponse<Long> cancelInterestProductByRegisterProduct(@PathVariable Long productId,
                                                                        @PathVariable Long interestedUserId) {
        try {
            final Long cancelId = interestProductService.cancelByRegisterProduct(productId, interestedUserId);
            return DefaultResponse.res(OK, CANCEL_INTEREST_PRODUCT, cancelId);
        }catch (Exception e){
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, CANCEL_INTEREST_PRODUCT_FAIL);
        }
    }

    private List<ProductShowResponse> toProductShowResponses(List<InterestProduct> interestProductList) {
        return interestProductList.stream()
                .map(p -> new ProductShowResponse(p.getProduct()))
                .collect(Collectors.toList());
    }
}
