package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.api.dto.request.ProductGradeUpdateRequest;
import com.toy.jeongoo.product.service.ProductUpdateService;
import com.toy.jeongoo.utils.DefaultResponse;
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

    @PutMapping("/{productId}")
    public DefaultResponse<Long> update(@PathVariable Long productId,
                                        ProductBasicInfoRequest productBasicInfoRequest,
                                        @RequestPart(name = "imageFile", required = false) List<MultipartFile> imageFiles,
                                        @RequestPart(name = "videoFile", required = false) MultipartFile videoFile) {
        try {
            final Long product = productUpdateService.update(productId, productBasicInfoRequest, imageFiles, videoFile);
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception productException) {
            log.error(productException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_PRODUCT_FAIL);
        }
    }

    @PutMapping("/{productId}/grade")
    public DefaultResponse<Long> updateGrade(@PathVariable Long productId,
                                             @Valid @RequestBody ProductGradeUpdateRequest gradeUpdateRequest) {
        try {
            final Long product = productUpdateService.updateGrade(productId, gradeUpdateRequest);
            return DefaultResponse.res(OK, UPDATE_PRODUCT, product);
        } catch (Exception e) {
            log.error(e.getMessage());
            return DefaultResponse.res(BAD_REQUEST, UPDATE_PRODUCT_FAIL);
        }
    }
}
