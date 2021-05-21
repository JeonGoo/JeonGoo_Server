package com.toy.jeongoo.product.api;

import com.toy.jeongoo.product.api.dto.request.ProductBasicInfoRequest;
import com.toy.jeongoo.product.service.ProductRegistrationService;
import com.toy.jeongoo.utils.DefaultResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.toy.jeongoo.utils.ResponseMessage.*;
import static com.toy.jeongoo.utils.StatusCode.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRegisterController {

    private final ProductRegistrationService registrationService;

    @PostMapping("/users/{userId}")
    public DefaultResponse<Long> register(@PathVariable Long userId,
                                          ProductBasicInfoRequest productBasicInfoRequest,
                                          @ApiParam(name = "등록할 상품 이미지 파일") @RequestPart(name = "imageFiles", required = false) List<MultipartFile> imageFiles,
                                          @ApiParam(name = "등록할 상품 동영상 파일") @RequestPart(name = "videoFile", required = false) MultipartFile videoFile) {
        try {
            log.info(productBasicInfoRequest.getDescription());
            final Long registrationId = registrationService.register(productBasicInfoRequest, imageFiles, videoFile, userId);
            return DefaultResponse.res(CREATED, REGISTER_PRODUCT, registrationId);
        } catch (Exception productRegistrationException) {
            log.error(productRegistrationException.getMessage());
            return DefaultResponse.res(BAD_REQUEST, REGISTER_PRODUCT_FAIL);
        }
    }
}
