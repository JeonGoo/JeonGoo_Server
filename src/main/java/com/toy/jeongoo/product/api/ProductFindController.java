package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductShowDetailWIthInterestedStatusResponse;
import com.toy.jeongoo.product.api.dto.response.ProductShowDetailResponse;
import com.toy.jeongoo.product.model.Product;
import com.toy.jeongoo.product.model.interest.InterestProduct;
import com.toy.jeongoo.product.service.ProductFindService;
import com.toy.jeongoo.product.service.interest.InterestProductService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @ApiOperation(value = "상품 조회", notes = "Product의 id로 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 조회 성공"),
            @ApiResponse(code = 400, message = "상품 조회 실패")
    })
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

    @ApiOperation(value = "상품 및 관심 여부 조회", notes = "해당 상품에 대한 내용 및 관심 여부를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 조회 성공"),
            @ApiResponse(code = 400, message = "상품 조회 실패")
    })
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

    @ApiOperation(value = "상품 전체 조회", notes = "모든 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 조회 성공"),
            @ApiResponse(code = 400, message = "상품 조회 실패")
    })
    @GetMapping
    public DefaultResponse<List<ProductShowDetailResponse>> showAllProductsDetailPage(Pageable pageable) {
        try {
            log.info(String.valueOf(pageable.getOffset()));
            log.info(String.valueOf(pageable.getPageSize()));
            final List<Product> productList = productFindService.findAllProductPage(pageable);
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowDetailResponseList(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @ApiOperation(value = "회원이 등록한 상품 조회", notes = "회원이 등록한 상품을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 조회 성공"),
            @ApiResponse(code = 400, message = "상품 조회 실패")
    })
    @GetMapping("/users/{userId}")
    public DefaultResponse<List<ProductShowDetailResponse>> findAllProductPageByUser(@PathVariable Long userId, Pageable pageable) {
        try {
            final List<Product> productList = productFindService.findAllProductPageByUser(userId, pageable);
            return DefaultResponse.res(OK, SHOW_PRODUCT, toProductShowDetailResponseList(productList));
        } catch (Exception productShowException) {
            log.error(productShowException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, SHOW_PRODUCT_FAIL);
        }
    }

    @ApiOperation(value = "판매중인 상품 조회", notes = "등록된 상품중 팔리지 않은 상품만 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 조회 성공"),
            @ApiResponse(code = 400, message = "상품 조회 실패")
    })
    @GetMapping("/sale")
    public DefaultResponse<List<ProductShowDetailResponse>> showAllSaleProductsPage(Pageable pageable) {
        try {
            final List<Product> productList = productFindService.showAllSaleProductsPage(pageable);
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
