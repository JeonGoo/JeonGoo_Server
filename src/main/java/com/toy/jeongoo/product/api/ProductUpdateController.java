package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.service.ProductUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.List;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductUpdateController {

    private final ProductUpdateService productUpdateService;

    @ApiOperation(value = "상품 등급 수정", notes = "상품에 대한 등급을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 수정 성공"),
            @ApiResponse(code = 400, message = "상품 수정 실패")})
    @PutMapping("/{productId}")
    public DefaultResponse<Long> update(@PathVariable Long productId,
                                        @ApiParam(name = "수정하고 싶은 상품 내용") ProductBasicInfoRequest productBasicInfoRequest,
                                        @ApiParam(name = "수정하고 싶은 상품 이미지 파일") @RequestPart(name = "imageFiles", required = false) List<MultipartFile> imageFiles,
                                        @ApiParam(name = "수정하고 싶은 상품 동영상 파일") @RequestPart(name = "videoFile", required = false) MultipartFile videoFile) {
        try {
            final Long product = productUpdateService.update(productId, productBasicInfoRequest, imageFiles, videoFile);
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_PRODUCT_FAIL);
        }
    }

    @ApiOperation(value = "상품 등급 수정", notes = "상품에 대한 등급을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "상품 수정 성공"),
            @ApiResponse(code = 400, message = "상품 수정 실패")})
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
