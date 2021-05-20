package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.service.ProductDeleteService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "등록 상품 삭제", notes = "등록한 상품을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 삭제 성공"),
            @ApiResponse(code = 400, message = "상품 삭제 실패")
    })
    @DeleteMapping("/{productId}")
    public DefaultResponse<Long> delete(@ApiParam(name = "삭제할 상품 Id", required = true) @PathVariable Long productId) {
        try {
            final Long product = productDeleteService.delete(productId);
            return DefaultResponse.res(OK, DELETE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, DELETE_PRODUCT_FAIL);
        }
    }
}
