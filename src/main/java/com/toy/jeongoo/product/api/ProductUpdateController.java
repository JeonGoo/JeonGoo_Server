package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.api.dto.request.ProductUpdateRequest;
import com.toy.jeongoo.product.service.ProductUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductUpdateController {

    private final ProductUpdateService productUpdateService;

    @ApiOperation(value = "상품 정보 수정", notes = "상품에 대한 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 수정 성공"),
            @ApiResponse(code = 400, message = "상품 수정 실패")
    })
    @PutMapping("/{productId}")
    public DefaultResponse<Long> update(@PathVariable Long productId,
                                        @ApiParam(name = "수정하고 싶은 상품 내용") @RequestBody ProductUpdateRequest updateRequest) {
        try {
            final Long product = productUpdateService.update(productId, updateRequest.getProductBasicInfoRequest(), updateRequest.getFileInfoRequest());
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_PRODUCT_FAIL);
        }
    }

    @ApiOperation(value = "상품 등급 수정", notes = "상품에 대한 등급을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 수정 성공"),
            @ApiResponse(code = 400, message = "상품 수정 실패")
    })
    @PutMapping("/{productId}/grade")
    public DefaultResponse<Long> updateGrade(@PathVariable Long productId,
                                             @ApiParam(name = "수정하고 싶은 상품 등급", required = true) @Valid @RequestBody ProductGradeUpdateRequest gradeUpdateRequest) {
        try {
            final Long product = productUpdateService.updateGrade(productId, gradeUpdateRequest);
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_PRODUCT_FAIL);
        }
    }
}
